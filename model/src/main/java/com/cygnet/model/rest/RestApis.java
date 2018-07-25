package com.cygnet.model.rest;

import com.cygnet.framework.utils.Constants;
import com.cygnet.model.db.models.AlbumModel;
import com.cygnet.model.db.models.UserModel;
import com.cygnet.model.entities.request.LoginRequest;
import com.cygnet.model.entities.request.RegisterRequest;
import com.cygnet.model.entities.response.BaseResponse;
import com.cygnet.model.entities.response.LoginResponse;
import com.cygnet.model.entities.response.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Name : RestApis
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :Contains web services use in application
 */
public interface RestApis {

    @POST(Constants.ApiMethods.REGISTER_URL)
    Call<RegisterResponse> registerUser(@Body RegisterRequest aRegisterRequest);

    @POST(Constants.ApiMethods.LOGIN_URL)
    Call<LoginResponse> loginUser(@Body LoginRequest aRequest);

    @GET(Constants.ApiMethods.USER_LIST_URL)
    Call<BaseResponse<List<UserModel>>> getUserList(@Query("page") int pageId);

    @GET(Constants.ApiMethods.ALBUM_LIST_URL)
    Call<List<AlbumModel>> getAlbumList();

}
