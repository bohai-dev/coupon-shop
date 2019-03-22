package com.qiaosheng.coupon.service;

import com.qiaosheng.coupon.domain.ShopNotice;

import java.util.List;

/**
 * Created by cxy on 2019/3/22
 */
public interface NoticeService {
    /**
     * 批量插入
     * @param list
     * @return
     */
    int add(List<ShopNotice> list);

    int add(ShopNotice shopNotice);

    List selectAll();

    int deleteNotice(String noticeId);

    int updateNotice(ShopNotice shopNotice);
}
