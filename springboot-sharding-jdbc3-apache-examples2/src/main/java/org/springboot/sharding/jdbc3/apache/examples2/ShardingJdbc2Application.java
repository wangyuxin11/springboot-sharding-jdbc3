package org.springboot.sharding.jdbc3.apache.examples2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;



/**
 * 分库：user0, user1
 * 
 * 执行@Test类后, 
 * 		user0 库的 t_user0 表  
 * 				和 
 *  	user1 库的 t_user1 表 
 *  	存了数据，说明分库分表成功。
 * 
 * 
 * 
 * 
 * 
 * 
 * @author wangyx
 *
 */
@SpringBootApplication(
        scanBasePackages = {"org.springboot.sharding.jdbc3.apache"},
        exclude = {DruidDataSourceAutoConfigure.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ShardingJdbc2Application {
 
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbc2Application.class, args);
    }
 
}
