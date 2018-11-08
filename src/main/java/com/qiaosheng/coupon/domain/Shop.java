package com.qiaosheng.coupon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class Shop {
    private String shopId;

    @NotBlank(message = "店铺名称不能为空")
    private String shopName;
    @NotBlank(message = "店铺图片不能为空")
    private String introImages;
    @NotBlank(message = "店铺地址不能为空")
    private String shopAddress;
    @NotBlank(message = "联系方式不能为空")
    private String contactWay;
    @NotBlank(message = "店铺特色不能为空")
    private String features;
    @NotBlank(message = "店铺介绍不能为空")
    private String shopIntro;

    @NotBlank(message = "店铺营业时间不能为空")
    private String openTime;
    //店铺用餐高峰期不能为空
    private String heightTime;

    @NotBlank(message = "店铺室内设施不能为空")
    private String indoorDevice;

    @NotBlank(message = "店铺招牌菜品不能为空")
    private String signDish;

     //店内现有活动
    private String shopActivity;
    //浏览次数
    private Integer scanTimes;
    @NotBlank(message = "用户id不能为空")
    private String adminUserId;

    private String backupCoumn1;

    private String backupCoumn2;

    @NotBlank(message = "所属分类不能为空")
    private String classId;

    @NotBlank(message = "是否为热门店铺不能为空")
    private String isHot;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updataTime;
    //温馨提示
    private String warmPrompt;

    private String isDelete;

    private List<Coupon> couponList;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getIntroImages() {
        return introImages;
    }

    public void setIntroImages(String introImages) {
        this.introImages = introImages == null ? null : introImages.trim();
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay == null ? null : contactWay.trim();
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
    }

    public String getShopIntro() {
        return shopIntro;
    }

    public void setShopIntro(String shopIntro) {
        this.shopIntro = shopIntro == null ? null : shopIntro.trim();
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    public String getHeightTime() {
        return heightTime;
    }

    public void setHeightTime(String heightTime) {
        this.heightTime = heightTime == null ? null : heightTime.trim();
    }

    public String getIndoorDevice() {
        return indoorDevice;
    }

    public void setIndoorDevice(String indoorDevice) {
        this.indoorDevice = indoorDevice == null ? null : indoorDevice.trim();
    }

    public String getSignDish() {
        return signDish;
    }

    public void setSignDish(String signDish) {
        this.signDish = signDish == null ? null : signDish.trim();
    }

    public String getShopActivity() {
        return shopActivity;
    }

    public void setShopActivity(String shopActivity) {
        this.shopActivity = shopActivity == null ? null : shopActivity.trim();
    }

    public Integer getScanTimes() {
        return scanTimes;
    }

    public void setScanTimes(Integer scanTimes) {
        this.scanTimes = scanTimes;
    }

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId == null ? null : adminUserId.trim();
    }

    public String getBackupCoumn1() {
        return backupCoumn1;
    }

    public void setBackupCoumn1(String backupCoumn1) {
        this.backupCoumn1 = backupCoumn1 == null ? null : backupCoumn1.trim();
    }

    public String getBackupCoumn2() {
        return backupCoumn2;
    }

    public void setBackupCoumn2(String backupCoumn2) {
        this.backupCoumn2 = backupCoumn2 == null ? null : backupCoumn2.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot == null ? null : isHot.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public String getWarmPrompt() {
        return warmPrompt;
    }

    public void setWarmPrompt(String warmPrompt) {
        this.warmPrompt = warmPrompt == null ? null : warmPrompt.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", introImages='" + introImages + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", features='" + features + '\'' +
                ", shopIntro='" + shopIntro + '\'' +
                ", openTime='" + openTime + '\'' +
                ", heightTime='" + heightTime + '\'' +
                ", indoorDevice='" + indoorDevice + '\'' +
                ", signDish='" + signDish + '\'' +
                ", shopActivity='" + shopActivity + '\'' +
                ", scanTimes=" + scanTimes +
                ", adminUserId='" + adminUserId + '\'' +
                ", backupCoumn1='" + backupCoumn1 + '\'' +
                ", backupCoumn2='" + backupCoumn2 + '\'' +
                ", classId='" + classId + '\'' +
                ", isHot='" + isHot + '\'' +
                ", createTime=" + createTime +
                ", updataTime=" + updataTime +
                ", warmPrompt='" + warmPrompt + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", couponList=" + couponList +
                '}';
    }
}