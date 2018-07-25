package com.cygnet.model.entities.response;


/**
 * Name : ForgotPasswordResponse
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :This class will holds the parameter of forgot password API response.
 */
public class ForgotPasswordResponse {

    private int mStatus;
    private String mMessage;

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
