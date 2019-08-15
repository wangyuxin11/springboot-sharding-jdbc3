package org.springboot.sharding.jdbc3.mybaits.p;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.springboot.sharding.jdbc3.mybaits.p.mapper")
public class Mybatis1MultipleDataSourcesApplication {

	
	/**
	 * 
	 * 
	 * springboot-mybatis多数据源的两种整合方法
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Mybatis1MultipleDataSourcesApplication.class, args);
	}
}
