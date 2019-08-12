package org.springboot.sharding.jdbc3.multiple.jpa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;



/**
 * 
 * 2\由于我们禁掉了自动数据源配置，因些下一步就需要手动将这些数据源创建出来
 * 
 * 
 *
 */
@Configuration
public class DataSourceConfig {
	
    @Bean(name = "test0DataSource")
    @Qualifier("test0DataSource")
    @ConfigurationProperties(prefix="spring.datasource.test0")
    public DataSource zeroDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "test1DataSource")
    @Qualifier("test1DataSource")
    @ConfigurationProperties(prefix="spring.datasource.test1")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test2DataSource")
    @Qualifier("test2DataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.test2")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    
	
}
