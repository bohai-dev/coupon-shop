package com.qiaosheng.coupon.exception;

/**
 * Cteated by cxy on 2018/11/6
 */
public class CouponException extends Exception {

    private String errorCode;
    private String errorMsg;

    public CouponException(ErrorConstant errorConstant) {

        this.errorCode=errorConstant.getErrorCode();
        this.errorMsg=errorConstant.getErrorMsg();
    }

    public CouponException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
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
}
