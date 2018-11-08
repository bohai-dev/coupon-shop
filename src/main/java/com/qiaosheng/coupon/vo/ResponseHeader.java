package com.qiaosheng.coupon.vo;

import com.qiaosheng.coupon.exception.ErrorConstant;

public class ResponseHeader {

    private String errorCode=ErrorConstant.SUCCESS.getErrorCode();
    private String errorMsg=ErrorConstant.SUCCESS.getErrorMsg();

    public ResponseHeader(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ResponseHeader() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ResponseHeader{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
