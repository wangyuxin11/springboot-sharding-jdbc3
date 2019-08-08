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
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class ShardingJdbc1Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
