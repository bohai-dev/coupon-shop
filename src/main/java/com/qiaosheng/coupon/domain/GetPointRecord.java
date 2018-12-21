package com.qiaosheng.coupon.domain;

import java.util.Date;

public class GetPointRecord {
    private Long custId;

    private String shopId;

    private Long childId;

    private Integer recordFlg;

    private Date createTime;

    private String bakStr;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Integer getRecordFlg() {
        return recordFlg;
    }

    public void setRecordFlg(Integer recordFlg) {
        this.recordFlg = recordFlg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBakStr() {
        return bakStr;
    }

    public void setBakStr(String bakStr) {
        this.bakStr = bakStr == null ? null : bakStr.trim();
    }
}