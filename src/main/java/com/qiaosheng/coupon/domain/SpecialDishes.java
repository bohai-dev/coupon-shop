package com.qiaosheng.coupon.domain;

public class SpecialDishes {
    private String dishId;

    private String shopId;

    private String imagePath;

    private String dishPrice;

    private String dishName;

    private String isDelete;

    private String backColumn1;

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

    public String getBackColumn1() {
        return backColumn1;
    }

    public void setBackColumn1(String backColumn1) {
        this.backColumn1 = backColumn1 == null ? null : backColumn1.trim();
    }

    public String getBackColumn2() {
        return backColumn2;
    }

    public void setBackColumn2(String backColumn2) {
        this.backColumn2 = backColumn2 == null ? null : backColumn2.trim();
    }
}