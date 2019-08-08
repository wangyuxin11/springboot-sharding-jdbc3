package org.springboot.sharding.jdbc3.apache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;


@SpringBootApplication(
        scanBasePackages = {"org.springboot.sharding.jdbc3.apache"},
        exclude = {DruidDataSourceAutoConfigure.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ShardingJdbcAplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcAplication.class, args);
    }
	
	
}
