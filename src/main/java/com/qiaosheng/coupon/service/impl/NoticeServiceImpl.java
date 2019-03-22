package com.qiaosheng.coupon.service.impl;

import com.qiaosheng.coupon.dao.ShopNoticeMapper;
import com.qiaosheng.coupon.domain.ShopNotice;
import com.qiaosheng.coupon.service.NoticeService;
import com.qiaosheng.coupon.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by cxy on 2019/3/22
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    ShopNoticeMapper noticeMapper;

    @Override
    public int add(List<ShopNotice> list) {
        for (ShopNotice shopNotice : list){
            shopNotice.setNoticeId(CommonUtils.generateId());
            shopNotice.setIsDelete(0);
            shopNotice.setCreateTime(new Date());
            shopNotice.setUpdateTime(new Date());
            }
        int count=noticeMapper.insertList(list);
        return  count;
    }

    @Override
    public int add(ShopNotice shopNotice) {
        shopNotice.setNoticeId(CommonUtils.generateId());
        shopNotice.setIsDelete(0);
        shopNotice.setCreateTime(new Date());
        shopNotice.setUpdateTime(new Date());

        return noticeMapper.insertSelective(shopNotice);
    }

    @Override
    public List selectAll() {
        return noticeMapper.selectAll();
    }

    @Override
    public int deleteNotice(String noticeId) {
        ShopNotice shopNotice=new ShopNotice();
        shopNotice.setNoticeId(noticeId);
        shopNotice.setIsDelete(1);
        shopNotice.setUpdateTime(new Date());
        return noticeMapper.updateByPrimaryKeySelective(shopNotice);
    }

    @Override
    public int updateNotice(ShopNotice shopNotice) {
        shopNotice.setUpdateTime(new Date());
        return noticeMapper.updateByPrimaryKeySelective(shopNotice);
    }
}
