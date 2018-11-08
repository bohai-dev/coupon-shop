package com.qiaosheng.coupon.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AdminUser {
    private String userId;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 20,message = "用户名最多为20个字符")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 20,message = "密码长度必须在6-20位之间")
    private String userPwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}