package org.springboot.sharding.jdbc3.mybaits.jta;

import org.mybatis.spring.annotation.MapperScan;
import org.springboot.sharding.jdbc3.mybaits.jta.datasource.Datasource1;
import org.springboot.sharding.jdbc3.mybaits.jta.datasource.Datasource2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;




@SpringBootApplication
// @EnableConfigurationProperties注解使@ConfigurationProperties注解生效
@EnableConfigurationProperties(value = { Datasource1.class, Datasource2.class })
@MapperScan("org.springboot.sharding.jdbc3.mybaits.jta.mapper")
public class MultipleDataSourcesJtaApplication {

	
	/*
	 * 
	 * >>>> 通过jta-atomikos解决传统项目多数据源事务管理问题  >>>> AtomikosDataSourceConfig1 / AtomikosDataSourceConfig2    
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(MultipleDataSourcesJtaApplication.class, args);
	}
	
}
