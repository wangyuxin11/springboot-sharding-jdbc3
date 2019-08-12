package org.springboot.sharding.jdbc3.dangdang.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 
 * 
 * 
 * @author wangyx
 *
 */
@Data
@ConfigurationProperties(prefix = "database8")
@Component
public class Database8Config {
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private String databaseName;

	public DataSource createDataSource() {
		DruidDataSource result = new DruidDataSource();
		result.setDriverClassName(getDriverClassName());
		result.setUrl(getUrl());
		result.setUsername(getUsername());
		result.setPassword(getPassword());
		return result;
	}
}
