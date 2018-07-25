package com.cygnet.model.repo;

import android.arch.lifecycle.LiveData;

import com.cygnet.framework.model.ApiResponse;
import com.cygnet.model.entities.request.LoginRequest;
import com.cygnet.model.entities.request.RegisterRequest;

/**
 * Name : HomeRepository
 *<br> Created by 1730 on 11/09/2017
 *<br> Modified by 1730 on 11/09/2017
 *<br> Purpose : Repository for abstract the communication of rest of the code to the Data sources such as Database or API calls.
 */

public interface HomeRepository extends BaseRepository {
    LiveData<ApiResponse> loginUserOnServer(LoginRequest aRequest);
    LiveData<ApiResponse> registerUserOnServer(RegisterRequest aRequest);
    LiveData<ApiResponse> getAlbumList();
    LiveData<ApiResponse> getUserList(int pageNumber);
}