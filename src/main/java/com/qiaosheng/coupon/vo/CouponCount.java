package com.qiaosheng.coupon.vo;

/**
 * Created by cxy on 2019/1/2
 */
public class CouponCount {

    private String shopId;
    private String shopName;
    private Integer counponCount;
    private Integer couponState;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getCounponCount() {
        return counponCount;
    }

    public void setCounponCount(Integer counponCount) {
        this.counponCount = counponCount;
    }

    public Integer getCouponState() {
        return couponState;
    }

    public void setCouponState(Integer couponState) {
        this.couponState = couponState;
    }
}
