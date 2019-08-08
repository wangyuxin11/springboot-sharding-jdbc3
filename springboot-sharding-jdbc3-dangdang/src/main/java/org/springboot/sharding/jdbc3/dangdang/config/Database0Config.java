package org.springboot.sharding.jdbc3.dangdang.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 本文使用了两个实体来接收数据库信息，并且创建数据源，也可以采用别的方式。
 * 首先看一下Database0Config和Database1Config两个类的代码。
 * 
 * @author wangyx
 *
 */
@Data
@ConfigurationProperties(prefix = "database0")
@Component
public class Database0Config {
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