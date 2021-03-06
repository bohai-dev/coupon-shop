package com.qiaosheng.coupon.exception;

/**
 * Created by cxy on 2018/11/6
 */
public enum ErrorConstant {


    SUCCESS("0000","ok"),
    PARAMS_VALIDATE_ERROR("0001","数据校验失败"),

    USER_NAME_EXISTS("0002","该用户名已存在"),
    USER_NOT_EXISTS("0003","用户名或密码错误"),
    USER_ID_REQUIRED("0004","userId不能为空"),
    SHOP_ID_REQUIRED("0005","shopId不能为空"),
    COUPON_ID_REQUIRED("0006","couponId不能为空"),
    LOGIN_CHECK_ERROR("0007","登录验证错误"),
    NOTICE_ID_REQUIRED("0008","noticeId不能为空"),




    SELECT_CONDITIONS_REQUIRED("0999","查询条件不能为空"),
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
