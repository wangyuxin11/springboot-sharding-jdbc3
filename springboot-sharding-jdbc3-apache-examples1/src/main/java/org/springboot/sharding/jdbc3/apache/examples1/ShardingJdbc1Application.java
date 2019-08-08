package org.springboot.sharding.jdbc3.apache.examples1;


/**
 * @program: 
 * @description:
 * 
 * 
 * 
 * https://blog.csdn.net/u013308490/article/details/94629860
 * 
 * spring boot + mybatis + sharding-jdbc + druid
 * 
 * 
 * 创建一个数据库user0，假设只有一张表t_user需要进行分表，分为2张表t_user0、t_user1，分片字段为主键id，采用取余的分片算法（%2）。
 * 
 * 创建对应的数据库及表
 * 
	CREATE DATABASE user0;
	 
	CREATE TABLE `t_user0` (
	  `id` bigint(20) NOT NULL,
	  `name` varchar(30) DEFAULT NULL COMMENT '名称',
	  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 0-男，1-女',
	  `phone` varchar(15) DEFAULT NULL COMMENT '电话',
	  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
	  `password` varchar(20) DEFAULT NULL COMMENT '密码',
	  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 
	CREATE TABLE `t_user1` (
	  `id` bigint(20) NOT NULL,
	  `name` varchar(30) DEFAULT NULL COMMENT '名称',
	  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 0-男，1-女',
	  `phone` varchar(15) DEFAULT NULL COMMENT '电话',
	  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
	  `password` varchar(20) DEFAULT NULL COMMENT '密码',
	  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * 
 * 使用sharding-jdbc-spring-boot-starter集成，本文使用版本为3.0.0
 * 
 * 
 * 
 * 至此，一个简单的spring boot下的sharding-jdbc分表项目初步完成，
 * 
 * 下面来编写测试用例，spring boot下我们可以方便的使用注解@SpringBootTest来进行单元测试。
 * 
 * 无需启动，直接运行测试类即可。
 * 
 * 
 * 运行结果：user0库的t_user0, t_user1 两个表一个存单，一个存双。
 * 
 * 再加一个表 t_user2, 修改yml中的配置，数据节点和按模运算分配。可以扩展到3个表
 * 
 * 
 * 
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;



/**
 * 
 * 无需启动，直接运行@test类
 * 
 * @author wangyx
 *
 */
@SpringBootApplication(
        scanBasePackages = {"org.springboot.sharding.jdbc3.apache"},
        exclude = {DruidDataSourceAutoConfigure.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ShardingJdbc1Application {
 
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbc1Application.class, args);
    }
 
}


