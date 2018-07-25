package com.cygnet.projecttemplate.mvvm.viewModels;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;
import android.arch.paging.PagedList;
import android.content.Context;

import com.cygnet.framework.model.ApiResponse;
import com.cygnet.framework.mvvm.views.MvvmView;
import com.cygnet.framework.utils.network.NetworkUtils;
import com.cygnet.model.ModelApp;
import com.cygnet.model.db.MyDatabase;
import com.cygnet.model.db.models.AlbumModel;
import com.cygnet.model.db.models.UserModel;
import com.cygnet.model.repo.HomeRepository;
import com.cygnet.model.repo.RepositoryImpl;
import com.cygnet.model.rest.TDataSource;
import com.cygnet.projecttemplate.R;
import com.cygnet.projecttemplate.mvvm.views.HomeView;
import com.cygnet.projecttemplate.utils.Constants;
import com.cygnet.projecttemplate.utils.DatabaseAsync;

/**
 * Name : HomeViewModel
 * <br> Created by 1730 on 10/9/2017
 * <br> Modified by 1730 on 11/9/2017
 * <br> Purpose :  The HomeViewModel extends ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
 * The ViewModel class allows data to survive configuration changes such as screen rotations.
 */

public class HomeViewModel extends BaseViewModel {

    public static final String TAG = BaseViewModel.class.getName();

    TDataSource tDataSource;

    public Context mContext;
    private MyDatabase mDatabase;
    private MediatorLiveData<ApiResponse> mAlbumLiveData;
    private MediatorLiveData<ApiResponse> mUserLiveData;
    public LiveData<PagedList<AlbumModel>> pagedAlbumList;
    public LiveData<PagedList<UserModel>> pagedUserList;
    private HomeRepository mHomeRepo;
    private HomeView mHomeView;

    // No argument constructor
    public HomeViewModel() {

    }

    @Override
    public ViewModel inIt(Context mContext, MvvmView mvpView) {
        this.mContext = mContext;
        this.mHomeView = (HomeView) mvpView;
        mDatabase = ModelApp.getAppInstance().getDbInstance();
        mAlbumLiveData = new MediatorLiveData<>();
        mUserLiveData = new MediatorLiveData<>();
        mHomeRepo = new RepositoryImpl();

        pagedAlbumList = mDatabase.albumDao().getPagedAlbumList().create(
                0,          // initial load position
                new PagedList.Config.Builder()
                        .setPageSize(10)
                        .setPrefetchDistance(9)
                        .build());


        pagedUserList = new LivePagedListProvider<Integer, UserModel>() {
            @Override
            protected DataSource<Integer, UserModel> createDataSource() {
                tDataSource = new TDataSource();
                return tDataSource;
            }
        }.create(0,         // initial load position
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPageSize(3)
//                                    .setInitialLoadSizeHint(9)
                        .build());


        return super.inIt(mContext, mvpView);
    }

    /**
     * Name : getPagedAlbumList
     * <br> Created by 1730 on 10/12/2017
     * <br> Modified by 1730 on 10/12/2017
     * <br> Purpose : To get updated AlbumModel called from HomeFragemnt
     *
     * @return this method will return Livedata of PagedList<AlbumModel>
     */
    public LiveData<PagedList<AlbumModel>> getPagedAlbumList() {
        return pagedAlbumList;
    }


    /**
     * Name : loadAlbumResponse
     * <br> Created by 1730 on 11/10/2017
     * <br> Modified by 1730 on 11/10/2017
     * <br> Purpose : This method will be called to get the Album list from API
     *
     * @return
     */
    public LiveData<ApiResponse> loadAlbumResponse() {
        if (!NetworkUtils.isNetworkAvailable(mContext)) {
            mHomeView.noInternetConnection(() -> loadAlbumResponse());
        } else {
            mHomeView.showLoader(mContext.getString(R.string.message_loader_loading_albums));
            mAlbumLiveData.observe((LifecycleOwner) mContext, apiResponse -> mHomeView.hideLoader());
            mAlbumLiveData.addSource(
                    mHomeRepo.getAlbumList(), apiResponse -> new DatabaseAsync<ApiResponse>(mContext,
                            mDatabase,
                            apiResponse,
                            Constants.Database.INSERT_ALBUMLIST,
                            result -> mAlbumLiveData.setValue(apiResponse)
                    ).execute()
            );
        }
        return mAlbumLiveData;
    }

    /**
     * Name : getPagedUserList
     * <br> Created by 1730 on 10/12/2017
     * <br> Modified by 1730 on 10/12/2017
     * <br> Purpose : To get updated UserModel called from HomeFragment.
     *
     * @return this method will return Livedata of PagedList<UserModel>
     */
    public LiveData<PagedList<UserModel>> getPagedUserList() {
        return pagedUserList;
    }


    /**
     * Name : loadUserResponse
     * <br> Created by 1730 on 11/10/2017
     * <br> Purpose : This method will be called to get the user list from API
     * <br> Modified by 1730 on 11/10/2017
     *
     * @return
     */
    public LiveData<ApiResponse> loadUserResponse(int pageId) {
        if (!NetworkUtils.isNetworkAvailable(mContext)) {
            mHomeView.noInternetConnection(() -> loadUserResponse(pageId));
        } else {
            mHomeView.showLoader(mContext.getString(R.string.message_loader_loading_users));
            mUserLiveData.observe((LifecycleOwner) mContext, apiResponse -> mHomeView.hideLoader());
            mUserLiveData.addSource(
                    mHomeRepo.getUserList(pageId), apiResponse -> new DatabaseAsync<ApiResponse>(mContext,
                            mDatabase,
                            apiResponse,
                            Constants.Database.INSERT_USERLIST,
                            result -> mUserLiveData.setValue(apiResponse)
                    ).execute()
            );
        }
        return mUserLiveData;
    }


}
