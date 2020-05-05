
package com.example.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ErrorResponse {

    @Expose
    @SerializedName("status_code")
    private Long mStatusCode;

    @Expose
    @SerializedName("status_message")
    private String mStatusMessage;

    @Expose
    @SerializedName("success")
    private Boolean mSuccess;

    public Long getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(Long statusCode) {
        mStatusCode = statusCode;
    }

    public String getStatusMessage() {
        return mStatusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        mStatusMessage = statusMessage;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
