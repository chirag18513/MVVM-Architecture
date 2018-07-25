package com.cygnet.projecttemplate.mvvm.viewModels;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.cygnet.framework.model.APIError;
import com.cygnet.framework.model.ApiResponse;
import com.cygnet.framework.mvvm.views.MvvmView;
import com.cygnet.framework.utils.network.NetworkUtils;
import com.cygnet.model.ModelApp;
import com.cygnet.model.db.MyDatabase;
import com.cygnet.model.entities.request.LoginRequest;
import com.cygnet.model.entities.response.LoginResponse;
import com.cygnet.model.repo.HomeRepository;
import com.cygnet.model.repo.RepositoryImpl;
import com.cygnet.projecttemplate.R;
import com.cygnet.projecttemplate.mvvm.views.LoginView;

/**
 * Name : LoginViewModel
 *<br> Created by 1730 on 10/9/2017
 *<br> Modified by 1730 on 11/9/2017
 *<br> Purpose :  The LoginViewModel extends ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
 *  The ViewModel class allows data to survive configuration changes such as screen rotations.
 */

public class LoginViewModel extends BaseViewModel {

    public static final String TAG = BaseViewModel.class.getName();

    private MyDatabase mDatabase;
    private MediatorLiveData<ApiResponse> mApiResponse;
    private HomeRepository mHomeRepo;
    private LoginView mLoginView;
    public Context mContext;

    // No argument constructor
    public LoginViewModel() {
    }

    @Override
    public ViewModel inIt(Context mContext, MvvmView mvpView) {
        this.mContext = mContext;
        this.mLoginView = (LoginView) mvpView;
        mApiResponse = new MediatorLiveData<>();
        mHomeRepo = new RepositoryImpl();
        mDatabase = ModelApp.getAppInstance().getDbInstance();

        return super.inIt(mContext, mvpView);
    }

    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return mApiResponse;
    }

    public LiveData<ApiResponse> loadResponse(final String sEmail, final String sPassword) {

        LoginRequest mRequest = new LoginRequest();
        mRequest.setEmail(sEmail);
        mRequest.setPassword(sPassword);

        mApiResponse.addSource(
                mHomeRepo.loginUserOnServer(mRequest), apiResponse -> mApiResponse.setValue(apiResponse)
        );
        return mApiResponse;
    }


    public void loginUser(String sUserName, String sPassword){
        if (!NetworkUtils.isNetworkAvailable(mContext)) {
            Toast.makeText(mContext, "Please check internet connection", Toast.LENGTH_SHORT).show();
            mLoginView.noInternetConnection(() -> loginUser(sUserName, sPassword));
        } else {
            mLoginView.showLoader(mContext.getString(R.string.message_loader_logging));
            loadResponse(sUserName, sPassword);
            getApiResponse().observe((LifecycleOwner) mContext, apiResponse -> {
                mLoginView.hideLoader();
                if (apiResponse != null){
                    if (apiResponse.getSingalData() instanceof APIError) {
//                      HandleError
                        mLoginView.apiError((APIError) apiResponse.getSingalData());
                    } else {
                        LoginResponse mLoginResponse = apiResponse.getSingalData() instanceof LoginResponse ? (LoginResponse) apiResponse.getSingalData() : new LoginResponse();
                        if (mLoginResponse != null && !TextUtils.isEmpty(mLoginResponse.getToken())){
                            Log.d(TAG, mLoginResponse.getToken());
                            mLoginView.onLoginSuccess(mLoginResponse);
                        }
                    }
                }
            });
        }
    }

}
