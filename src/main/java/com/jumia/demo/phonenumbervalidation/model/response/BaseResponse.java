package com.jumia.demo.phonenumbervalidation.model.response;

public class BaseResponse {

    private boolean errorStatus;
    private String responseStatus;
    private String message;

    BaseResponse(boolean errorStatus, String responseStatus, String message) {
        this.errorStatus = errorStatus;
        this.responseStatus = responseStatus;
        this.message = message;
    }


    BaseResponse(boolean errorStatus, String message) {
        this.errorStatus = errorStatus;
        this.responseStatus = message;
    }


    BaseResponse() {
    }

    public boolean isErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(boolean errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
