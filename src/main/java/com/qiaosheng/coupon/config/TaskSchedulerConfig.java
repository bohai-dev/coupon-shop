package com.qiaosheng.coupon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by cxy on 2018/11/28
 */
@Configuration
@ComponentScan("com.qiaosheng.coupon.service.taskscheduler")
@EnableScheduling    //开启对计划任务的支持
public class TaskSchedulerConfig {
}
