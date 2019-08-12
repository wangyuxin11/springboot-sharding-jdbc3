package org.springboot.sharding.jdbc3.multiple.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * 
 * 接下来是对应test数据库的配置，其中需要说一下的是@EnableJpaRepositories注解里面的basePackages属性对应的是这个数据源对应的repository（因为本文使用的是jpa）， @Qualifier 注解内的value要和DataSourceConfig的值一致即可。
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryZero",
        transactionManagerRef="transactionManagerZero",
        basePackages= { "org.springboot.sharding.jdbc3.multiple.repository.datasource0" })
public class Test0DataSourceConfig {
	
    @Autowired
    @Qualifier("test0DataSource")
    private DataSource dataSource0;

    @Primary
    @Bean(name = "entityManagerZero")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryZero")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource0)
                .properties(getVendorProperties(dataSource0))
                .packages("org.springboot.sharding.jdbc3.multiple.entity.datasource0") //设置实体类所在位置
                .persistenceUnit("zeroPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Primary
    @Bean(name = "transactionManagerZero")
    public PlatformTransactionManager transactionManagerZero(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
}