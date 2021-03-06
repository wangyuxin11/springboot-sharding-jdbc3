package org.springboot.sharding.jdbc3.dangdang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 	原理->id-generator-baidu-consumer
 * 
 * @author wangyx
 *
 */
@Configuration
@ImportResource(locations = { "classpath:uid/cached-uid-spring.xml" })
public class UidConfig {

}
