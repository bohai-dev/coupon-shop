package com.qiaosheng.coupon.vo;

public class ReturnBody<T> extends ResponseHeader{
    
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
