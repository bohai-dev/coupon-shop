package com.qiaosheng.coupon.domain;

public class SpecialDishes {
    private String dishId;

    private String shopId;

    private String imagePath;

    private String dishPrice;

    private String dishName;

    private String isDelete;

    private String dishUnit;

    private String backColumn2;

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId == null ? null : dishId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice == null ? null : dishPrice.trim();
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName == null ? null : dishName.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getBackColumn2() {
        return backColumn2;
    }

    public void setBackColumn2(String backColumn2) {
        this.backColumn2 = backColumn2 == null ? null : backColumn2.trim();
    }

    public String getDishUnit() {
        return dishUnit;
    }

    public void setDishUnit(String dishUnit) {
        this.dishUnit = dishUnit;
    }

    @Override
    public String toString() {
        return "SpecialDishes{" +
                "dishId='" + dishId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", dishPrice='" + dishPrice + '\'' +
                ", dishName='" + dishName + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", dishUnit='" + dishUnit + '\'' +
                ", backColumn2='" + backColumn2 + '\'' +
                '}';
    }
}