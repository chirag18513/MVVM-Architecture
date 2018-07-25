package com.cygnet.model.entities.response;

import com.cygnet.framework.model.ApiResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Name : LoginResponse
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :This class will holds the parameter of login API response.
 */
public class LoginResponse extends ApiResponse<LoginResponse> {

    @JsonProperty("token")
    private String mToken;

    public String getToken() {
        return mToken;
    }

    public void setToken(String aToken) {
        mToken = aToken;
    }
}
