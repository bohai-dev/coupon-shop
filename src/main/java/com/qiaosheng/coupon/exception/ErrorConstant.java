package com.qiaosheng.coupon.exception;

/**
 * Cteated by cxy on 2018/11/6
 */
public enum ErrorConstant {


    SUCCESS("0000","ok"),
    PARAMS_VALIDATE_ERROR("0001","数据校验失败"),
    USER_NAME_EXISTS("0002","该用户名已存在"),
    USER_NOT_EXISTS("0003","用户名或密码错误"),



    UNKNOW_EXCEPTION("1000", "程序内部异常");

    private String errorCode;
    private String errorMsg;

    ErrorConstant(String errorCode, String errorMsg) {
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
