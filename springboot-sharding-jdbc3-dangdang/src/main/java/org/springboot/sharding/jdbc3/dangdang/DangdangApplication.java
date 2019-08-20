package org.springboot.sharding.jdbc3.dangdang;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
/**
 * 使用SpringBoot2.0.3，SpringData-JPA，Druid连接池，和当当的sharding-jdbc。
 * 
 * https://blog.csdn.net/weixin_33805557/article/details/88178495
 * 
 * 
 * 
 * 这个实例是按主键id分，<20存database0, >20存database1  在database中再一次分表。
 * 
 * 
 * 
 * SpringBoot使用Sharding-JDBC分库分表
 * https://juejin.im/post/5c5251b1518825469414ef4a
 * 
 * 
 * 
 * 
 * 一、DatabaseShardingAlgorithm、TableShardingAlgorithm 
 *分库策略是： GoodsId<=20使用database0库 其余使用database1库 
 * 分表规则：GoodsType为奇数使用goods_1表 GoodsType为偶数使用goods_0表， 
 * 
 * 
 * 二、AccDatabaseShardingAlgorithm 、 AccTableShardingAlgorithm
 * 分库策略：除模10
 * 分表策略：除模10
 * 
 * 
 * 
 * 
 * @author wangyx
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties
@MapperScan({"org.springboot.sharding.jdbc3.dangdang", "org.springboot.sharding.jdbc3.generator.uid.worker.dao"})
public class DangdangApplication {

	public static void main(String[] args) {
		SpringApplication.run(DangdangApplication.class, args);
	}

}
