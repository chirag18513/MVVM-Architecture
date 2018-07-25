package com.cygnet.model.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hrdudhat on 1/8/2016.
 */
public class BaseResponse<T> {

    @JsonProperty("data")
    T data;

    public T getData() {
        return data;
    }

    public void setData(T aData) {
        data = aData;
    }
}
