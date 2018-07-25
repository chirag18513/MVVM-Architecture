package com.cygnet.framework.model;

import com.cygnet.framework.utils.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Name : APIError
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :    Common API Error Class which will used to handle all the API related errors.
 */
public class APIError extends ApiResponse<APIError> {

    @JsonProperty(Constants.ApiCodes.ERROR_KEY)
    private int mStatusCode;
    @JsonProperty(Constants.ApiCodes.ERROR_MESSAGE_KEY)
    private String mStatusMessage;

    public APIError() {
    }

    public APIError(int aStatusCode, String aStatusMessage) {
        mStatusCode = aStatusCode;
        mStatusMessage = aStatusMessage;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int aStatusCode) {
        mStatusCode = aStatusCode;
    }

    public String getStatusMessage() {
        return mStatusMessage;
    }

    public void setStatusMessage(String aStatusMessage) {
        mStatusMessage = aStatusMessage;
    }
}
