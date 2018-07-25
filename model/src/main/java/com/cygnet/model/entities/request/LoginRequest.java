package com.cygnet.model.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * Name : LoginRequest
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :This class will keep the request parameters for login.
 */
public class LoginRequest {

    @JsonProperty("email")
    private String mEmail;

    @JsonProperty("password")
    private String mPassword;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String aEmail) {
        mEmail = aEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String aPassword) {
        mPassword = aPassword;
    }
}
