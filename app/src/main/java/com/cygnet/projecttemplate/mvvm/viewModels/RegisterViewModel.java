package com.cygnet.projecttemplate.mvvm.viewModels;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cygnet.framework.model.APIError;
import com.cygnet.framework.model.ApiResponse;
import com.cygnet.framework.mvvm.views.MvvmView;
import com.cygnet.framework.utils.network.NetworkUtils;
import com.cygnet.model.ModelApp;
import com.cygnet.model.db.MyDatabase;
import com.cygnet.model.entities.request.RegisterRequest;
import com.cygnet.model.entities.response.RegisterResponse;
import com.cygnet.model.repo.HomeRepository;
import com.cygnet.model.repo.RepositoryImpl;
import com.cygnet.projecttemplate.R;
import com.cygnet.projecttemplate.mvvm.views.RegisterView;

/**
 * Name : RegisterViewModel
 *<br> Created by 1730 on 11/9/2017
 *<br> Modified by 1730 on 11/9/2017
 *<br> Purpose :  The RegisterViewModel extends ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
 *  The ViewModel class allows data to survive configuration changes such as screen rotations.
 */

public class RegisterViewModel extends BaseViewModel {

    public static final String TAG = BaseViewModel.class.getName();

    public Context mContext;
    private MyDatabase mDatabase;
    private MediatorLiveData<ApiResponse> mRegLiveData;
    private HomeRepository mHomeRepo;
    private RegisterView mRegView;

    // No argument constructor
    public RegisterViewModel() {
    }

    @Override
    public ViewModel inIt(Context mContext, MvvmView mvpView) {
        this.mContext = mContext;
        this.mRegView = (RegisterView) mvpView;
        mRegLiveData = new MediatorLiveData<>();
        mHomeRepo = new RepositoryImpl();
        mDatabase = ModelApp.getAppInstance().getDbInstance();

        return super.inIt(mContext, mvpView);
    }

    @NonNull
    public LiveData<ApiResponse> regLiveData() {
        return mRegLiveData;
    }


    /**
     * Name : registerUser
     *<br> Created by 1730 on 11/9/2017
     *<br> Modified by 1730 on 11/9/2017
     *<br> Purpose : This method will be called from the {@link com.cygnet.projecttemplate.views.fragments.RegisterFragment}
     * to register a new user on server.
     * @param sEmail    : E-Mail ID entered by the user.
     * @param sPassword : Password entered by the user.
     */
    public LiveData<ApiResponse> loadRegUserResponse(final String sEmail, final String sPassword) {

        RegisterRequest mRegisterRequest = new RegisterRequest();
        mRegisterRequest.setEmail(sEmail);
        mRegisterRequest.setPassword(sPassword);

        if(!NetworkUtils.isNetworkAvailable(mContext)){
            mRegView.noInternetConnection(() -> loadRegUserResponse(sEmail, sPassword));
        } else {
            mRegView.showLoader(mContext.getString(R.string.message_loader_registering));
            mRegLiveData.observe((LifecycleOwner) mContext, apiResponse -> {

                mRegView.hideLoader();

                if (apiResponse != null){
                    if (apiResponse.getSingalData() instanceof APIError) {
//                      HandleError
                        mRegView.apiError((APIError) apiResponse.getSingalData());
                    } else {
                        RegisterResponse mRegisterResponse = (apiResponse.getSingalData() instanceof RegisterResponse) ?
                                (RegisterResponse) apiResponse.getSingalData() : null;
                        if (mRegisterResponse != null){
                            mRegView.onUserRegistered(mRegisterResponse);
                        } else {
                            mRegView.apiError(new APIError(-1, mContext.getString(R.string.error_userRegistration)));
                        }
                    }
                }
            });
            mRegLiveData.addSource(
                    mHomeRepo.registerUserOnServer(mRegisterRequest), apiResponse -> mRegLiveData.setValue(apiResponse)
            );
        }
        return mRegLiveData;
    }


}
