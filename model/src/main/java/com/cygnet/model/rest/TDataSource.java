package com.cygnet.model.rest;

import android.arch.paging.DataSource;
import android.arch.paging.TiledDataSource;
import android.util.Log;

import com.cygnet.model.ModelApp;
import com.cygnet.model.db.MyDatabase;
import com.cygnet.model.db.models.UserModel;
import com.cygnet.model.entities.response.BaseResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Name : TDataSource
 * <br> Created by 1730 on 11/15/2017
 * <br> Modified by 1730 on 11/15/2017
 * <br> Purpose : For get user data with pagination.
 */

public class TDataSource extends TiledDataSource<UserModel> {

    private final MyDatabase mDatabase;
    private RestApis mApiService;
    private int pageNumber = 0;

    public TDataSource() {
        mApiService = ServiceGenerator.createService(RestApis.class);
        mDatabase = ModelApp.getAppInstance().getDbInstance();
    }

    @Override
    public int countItems() {
        return DataSource.COUNT_UNDEFINED;
    }

    @Override
    public List<UserModel> loadRange(int startPosition, int count) {
        List<UserModel> userModelList = new ArrayList();
        try {
            pageNumber += 1;
            Response<BaseResponse<List<UserModel>>> response = mApiService.getUserList(pageNumber).execute();
            if (response.isSuccessful() && response.code() == 200) {
                mDatabase.userDao().insertUserList(response.body().getData());
                userModelList.addAll(response.body().getData());
            } else {
                Log.e("API CALL", response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userModelList;
    }
}
