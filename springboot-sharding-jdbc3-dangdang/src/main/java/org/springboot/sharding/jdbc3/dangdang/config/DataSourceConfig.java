package org.springboot.sharding.jdbc3.dangdang.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springboot.sharding.jdbc3.dangdang.shardingalgorithm.AccDatabaseShardingAlgorithm;
import org.springboot.sharding.jdbc3.dangdang.shardingalgorithm.AccTableShardingAlgorithm;
import org.springboot.sharding.jdbc3.dangdang.shardingalgorithm.DatabaseShardingAlgorithm;
import org.springboot.sharding.jdbc3.dangdang.shardingalgorithm.TableShardingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;

/**
 * 
 */
@Configuration
//@ConfigurationProperties(prefix = DataSourceConstants.DATASOURCE_PREFIX)
//@MapperScan(basePackages = { DataSourceConstants.MAPPER_PACKAGE }, sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class DataSourceConfig {
	
//	private String url;    
//	private String username;    
//	private String password;    

	@Autowired
	private Database0Config database0Config;

	@Autowired
	private Database1Config database1Config;
	
	@Autowired
	private Database2Config database2Config;

	@Autowired
	private Database3Config database3Config;

	@Autowired
	private Database4Config database4Config;

	@Autowired
	private Database5Config database5Config;

	@Autowired
	private Database6Config database6Config;

	@Autowired
	private Database7Config database7Config;

	@Autowired
	private Database8Config database8Config;

	@Autowired
	private Database9Config database9Config;
	
	
	@Autowired
	private AccDatabaseShardingAlgorithm accDatabaseShardingAlgorithm;

	@Autowired
	private AccTableShardingAlgorithm accTableShardingAlgorithm;
	
	
	@Autowired
	private DatabaseShardingAlgorithm databaseShardingAlgorithm;

	@Autowired
	private TableShardingAlgorithm tableShardingAlgorithm;

	
	@Bean
	public DataSource getDataSource() throws SQLException {
		return buildDataSource();
	}
		
		
	/**
	 * 	构建datasource
	 * 	
	 * 	设置分库映射
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	//@Bean(name = "mybatisDataSource")
	private DataSource buildDataSource() throws SQLException {
		
		//	分库设置
		Map<String, DataSource> dataSourceMap = new HashMap<>(2);
		
		//	添加两个数据库database0和database1
		dataSourceMap.put(database0Config.getDatabaseName(), database0Config.createDataSource());
		dataSourceMap.put(database1Config.getDatabaseName(), database1Config.createDataSource());
		dataSourceMap.put(database2Config.getDatabaseName(), database2Config.createDataSource());
		dataSourceMap.put(database3Config.getDatabaseName(), database3Config.createDataSource());
		dataSourceMap.put(database4Config.getDatabaseName(), database4Config.createDataSource());
		dataSourceMap.put(database5Config.getDatabaseName(), database5Config.createDataSource());
		dataSourceMap.put(database6Config.getDatabaseName(), database6Config.createDataSource());
		dataSourceMap.put(database7Config.getDatabaseName(), database7Config.createDataSource());
		dataSourceMap.put(database8Config.getDatabaseName(), database8Config.createDataSource());
		dataSourceMap.put(database9Config.getDatabaseName(), database9Config.createDataSource());
		
		//	设置默认数据库
		DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, database0Config.getDatabaseName());

		//	分表策略1，大致思想就是将查询虚拟表Goods根据一定规则映射到真实表中去
		TableRule orderTableRule = TableRule.builder("goods")
				.actualTables(Arrays.asList("goods_0", "goods_1"))
				.databaseShardingStrategy(new DatabaseShardingStrategy("goods_id", databaseShardingAlgorithm))
				.tableShardingStrategy(new TableShardingStrategy("goods_type", tableShardingAlgorithm))
				.dataSourceRule(dataSourceRule).build();
		
		//	分表策略2
		TableRule accTableRule = TableRule.builder("acct")
				.generateKeyColumn("acct_id") //将acct_id作为分布式主键
				.actualTables(Arrays.asList("acct_0", "acct_1", "acct_2", "acct_3", "acct_4", "acct_5", "acct_6", "acct_7", "acct_8", "acct_9"))
				//	根据user_id 的 hashcode 来区分database
				.databaseShardingStrategy(new DatabaseShardingStrategy("hashcode", accDatabaseShardingAlgorithm))
				//	根据分布式自增id来区分table
				.tableShardingStrategy(new TableShardingStrategy("acct_id", accTableShardingAlgorithm))   //分表的不起做用，换成tableShardingAlgorithm 也不起作用 ，什么鬼, 换成acct_id就可以了，原来是用的mod_value没有起作用
				.dataSourceRule(dataSourceRule).build();

		//	分片策略
		ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule)
				.tableRules(Arrays.asList(orderTableRule, accTableRule))
//				.databaseShardingStrategy(new DatabaseShardingStrategy("goods_id", databaseShardingAlgorithm))
//				.tableShardingStrategy(new TableShardingStrategy("goods_type", tableShardingAlgorithm))
				.build();
		
		//	加载
		DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
		
		return dataSource;
	}

	
	/**
	 * 
	 * 	主键生成策略：推荐雪花生成器
	 * 
	 * @return
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		return new DefaultKeyGenerator();
	}
	
	
//	private DataSource mybatisDataSource(final String dataSourceName) throws SQLException {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(DataSourceConstants.DRIVER_CLASS);
//        dataSource.setUrl(String.format(url, dataSourceName));
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);        /* 配置初始化大小、最小、最大 */
//        dataSource.setInitialSize(1);
//        dataSource.setMinIdle(1);
//        dataSource.setMaxActive(20);        /* 配置获取连接等待超时的时间 */
//        dataSource.setMaxWait(60000);        /* 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
//        dataSource.setTimeBetweenEvictionRunsMillis(60000);        /* 配置一个连接在池中最小生存的时间，单位是毫秒 */
//        dataSource.setMinEvictableIdleTimeMillis(300000);
//
//        dataSource.setValidationQuery("SELECT 'x'");
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestOnReturn(false);        
//        /* 打开PSCache，并且指定每个连接上PSCache的大小。 如果用Oracle，则把poolPreparedStatements配置为true， mysql可以配置为false。分库分表较多的数据库，建议配置为false */
//        dataSource.setPoolPreparedStatements(false);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);        /* 配置监控统计拦截的filters */
//        dataSource.setFilters("stat,wall,log4j");        return dataSource;
//    }
//
//	 /**
//     * Sharding-jdbc的事务支持
//     *
//     * @return
//     */
//    @Bean(name = "mybatisTransactionManager")    
//    public DataSourceTransactionManager mybatisTransactionManager(@Qualifier("mybatisDataSource") DataSource dataSource) throws SQLException {
//    	return new DataSourceTransactionManager(dataSource);
//    }    
//    
//    @Bean(name = "mybatisSqlSessionFactory")    
//    public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource mybatisDataSource) throws Exception {
//    	final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(mybatisDataSource);        
//        return sessionFactory.getObject();
//    } 


}
