package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    @Select(value="select ADMIN_USER_SEQ.NEXTVAL from dual")
    String generateUserId();

    //根据用户名查询用户
    @Select("select * from ADMIN_USER where USER_NAME= #{userName}")
    AdminUser selectByUserName(@Param("userName") String userName);

    //根据用户名和密码查询用户
    @Select("select * from ADMIN_USER where USER_NAME= #{userName} and USER_PWD=#{passward}")
    AdminUser selectByNamePwd(@Param("userName")String userName,@Param("passward") String passward);

}