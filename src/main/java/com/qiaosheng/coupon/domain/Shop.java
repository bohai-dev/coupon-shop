package com.qiaosheng.coupon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class Shop {
    private String shopId;

    @NotBlank(message = "店铺名称不能为空")
    private String shopName;
    //@NotBlank(message = "店铺图片不能为空")
    private String introImages;
    @NotBlank(message = "店铺地址不能为空")
    private String shopAddress;
    @NotBlank(message = "联系方式不能为空")
    private String contactWay;
    @NotBlank(message = "店铺特色不能为空")
    private String features;
    @NotBlank(message = "店铺详细介绍不能为空")
    private String shopIntro;

    @NotBlank(message = "店铺营业时间不能为空")
    private String openTime;
    //店铺用餐高峰期不能为空
    private String heightTime;

    @NotBlank(message = "店铺室内设施不能为空")
    private String indoorDevice;

   // @NotBlank(message = "店铺招牌菜品不能为空")
    private String signDish;

     //店内现有活动
    private String shopActivity;
    //浏览次数
    private Integer scanTimes;
    @NotBlank(message = "用户id不能为空")
    private String adminUserId;

   //  private String backupCoumn1;
    //简介
    @NotBlank(message = "店铺简介不能为空")
    private String simpleIntro;

    //公众号文章地址
    private String articleUrl;

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

    //流动标题
    private String flowTitle;

    //流动内容
    private String flowContent;


    //经度
    private String longitude;

    //纬度
    private String latitude;

    //顶部图片路径
    private String topImagePath;

    //分享标题
    private String shareTitle;

    //分享图片路径
    private String shareImagePath;
    //招牌菜品
    private List<SpecialDishes> specialDishes;
    private List<Coupon> couponList;
    //地区
    private String shopArea;
    //人均消费
    private String averageConsume;

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

    public String getSimpleIntro() {
        return simpleIntro;
    }

    public void setSimpleIntro(String simpleIntro) {
        this.simpleIntro = simpleIntro;
    }

    public String getFlowTitle() {
        return flowTitle;
    }

    public void setFlowTitle(String flowTitle) {
        this.flowTitle = flowTitle;
    }

    public String getFlowContent() {
        return flowContent;
    }

    public void setFlowContent(String flowContent) {
        this.flowContent = flowContent;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTopImagePath() {
        return topImagePath;
    }

    public void setTopImagePath(String topImagePath) {
        this.topImagePath = topImagePath;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareImagePath() {
        return shareImagePath;
    }

    public void setShareImagePath(String shareImagePath) {
        this.shareImagePath = shareImagePath;
    }

    public List<SpecialDishes> getSpecialDishes() {
        return specialDishes;
    }

    public void setSpecialDishes(List<SpecialDishes> specialDishes) {
        this.specialDishes = specialDishes;
    }

    public String getShopArea() {
        return shopArea;
    }

    public void setShopArea(String shopArea) {
        this.shopArea = shopArea;
    }

    public String getAverageConsume() {
        return averageConsume;
    }

    public void setAverageConsume(String averageConsume) {
        this.averageConsume = averageConsume;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
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
                ", simpleIntro='" + simpleIntro + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", classId='" + classId + '\'' +
                ", isHot='" + isHot + '\'' +
                ", createTime=" + createTime +
                ", updataTime=" + updataTime +
                ", warmPrompt='" + warmPrompt + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", flowTitle='" + flowTitle + '\'' +
                ", flowContent='" + flowContent + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", topImagePath='" + topImagePath + '\'' +
                ", shareTitle='" + shareTitle + '\'' +
                ", shareImagePath='" + shareImagePath + '\'' +
                ", specialDishes=" + specialDishes +
                ", couponList=" + couponList +
                ", shopArea='" + shopArea + '\'' +
                ", averageConsume='" + averageConsume + '\'' +
                '}';
    }
}