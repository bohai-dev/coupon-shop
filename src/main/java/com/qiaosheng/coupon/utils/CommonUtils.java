package com.qiaosheng.coupon.utils;

import java.text.DecimalFormat;

/**
 * Cteated by cxy on 2018/11/7
 * 通用工具类
 */
public class CommonUtils {


    /**
     * 根据数字生成指定格式的编号
     * @param num
     * @return
     */
  public static String getNo(int num){
      DecimalFormat decimalFormat=new DecimalFormat("00000");
      String str = decimalFormat.format(num);
      System.out.println(str);
      return  str;
  }

}


