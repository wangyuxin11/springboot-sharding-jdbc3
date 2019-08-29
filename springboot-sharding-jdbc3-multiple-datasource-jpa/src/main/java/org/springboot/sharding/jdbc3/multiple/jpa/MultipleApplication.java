package org.springboot.sharding.jdbc3.multiple.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 
 * 	多数据源
 * 
 * 	1\首先要将spring boot自带的DataSourceAutoConfiguration禁掉，因为它会读取application.properties文件的spring.datasource.*属性并自动配置单数据源。	
 * 	在@SpringBootApplication注解中添加exclude属性即可
 * 
 * 
 *
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class MultipleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleApplication.class, args);
	}

}
