package org.springboot.sharding.jdbc3.apache.examples2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

@SpringBootApplication(
        scanBasePackages = {"org.springboot.sharding.jdbc3.apache"},
        exclude = {DruidDataSourceAutoConfigure.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ShardingJdbc2Application {
 
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbc2Application.class, args);
    }
 
}
