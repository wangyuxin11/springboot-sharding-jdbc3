package org.springboot.sharding.jdbc3.dangdang;


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
 * 
 * 
 * @author wangyx
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties
public class DangdangApplication {

	public static void main(String[] args) {
		SpringApplication.run(DangdangApplication.class, args);
	}

}
