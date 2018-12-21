package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.GetPointRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GetPointRecordMapper {
    int insert(GetPointRecord record);

    int insertSelective(GetPointRecord record);

    @Update("update GET_POINT_RECORD set RECORD_FLG = '1' ")
    int resetStatus();
}