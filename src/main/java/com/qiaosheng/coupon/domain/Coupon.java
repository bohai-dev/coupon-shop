package com.qiaosheng.coupon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Coupon {
    private String couponId;

    @NotBlank(message = "店铺id不能为空")
    private String shopId;

    //分享次数
    private Long shareTimes;
    @NotBlank(message = "优惠券类型不能为空")
    private String couponType;
    @NotBlank(message = "优惠券面值不能为空")
    private String couponValue;
    //兑换次数
    @NotBlank(message = "兑换次数不能为空")
    private Long exchangeTimes;
    private String backupColumn1;

    private String backupColumn2;

    private String isDelete;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer scanTimes;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public Long getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(Long shareTimes) {
        this.shareTimes = shareTimes;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue == null ? null : couponValue.trim();
    }

    public String getBackupColumn1() {
        return backupColumn1;
    }

    public void setBackupColumn1(String backupColumn1) {
        this.backupColumn1 = backupColumn1 == null ? null : backupColumn1.trim();
    }

    public String getBackupColumn2() {
        return backupColumn2;
    }

    public void setBackupColumn2(String backupColumn2) {
        this.backupColumn2 = backupColumn2 == null ? null : backupColumn2.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getScanTimes() {
        return scanTimes;
    }

    public void setScanTimes(Integer scanTimes) {
        this.scanTimes = scanTimes;
    }

    public Long getExchangeTimes() {
        return exchangeTimes;
    }

    public void setExchangeTimes(Long exchangeTimes) {
        this.exchangeTimes = exchangeTimes;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponId='" + couponId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shareTimes=" + shareTimes +
                ", couponType='" + couponType + '\'' +
                ", couponValue='" + couponValue + '\'' +
                ", exchangeTimes=" + exchangeTimes +
                ", backupColumn1='" + backupColumn1 + '\'' +
                ", backupColumn2='" + backupColumn2 + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", scanTimes=" + scanTimes +
                '}';
    }
}