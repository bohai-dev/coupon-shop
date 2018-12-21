package com.qiaosheng.coupon.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by cxy on 2018/12/21
 */
@Mapper
public interface GlobalMapper {

    @Select(value="select COUPON_SEQ.NEXTVAL from dual")
    String generateId();
}
