package org.springboot.sharding.jdbc3.dangdang;


import org.mybatis.spring.annotation.MapperScan;
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
 * 这个实例是按主键id分，<20存database0, >20存database1  在database中再一次分表。
 * 
 * 
 * 
 * SpringBoot使用Sharding-JDBC分库分表
 * https://juejin.im/post/5c5251b1518825469414ef4a
 * 
 * 
 * 
 * 
 * 一、DatabaseShardingAlgorithm、TableShardingAlgorithm 
 *分库策略是： GoodsId<=20使用database0库 其余使用database1库 
 * 分表规则：GoodsType为奇数使用goods_1表 GoodsType为偶数使用goods_0表， 
 * 
 * 
 * 二、AccDatabaseShardingAlgorithm 、 AccTableShardingAlgorithm
 * 分库策略：除模10
 * 分表策略：除模10
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
@MapperScan({"org.springboot.sharding.jdbc3.dangdang", "org.springboot.sharding.jdbc3.generator.uid.worker.dao"})
public class DangdangApplication {

	
	/**    理解Sharding jdbc原理    http://www.lanxinbase.com/?p=2090    */

	
//	分库策略算法GroupIDAndCollectTimeDBShardingAlgorithm
//	分表策略算法CollectTimeYYYYMMDDTBShardingAlgorithm
	
	
//	根据数据源策略和表策略，单分片与多分片，这两种组合一共产生了4中可供实现的分片算法的接
//
//	单分片键数据源分片算法	SingleKeyDatabaseShardingAlgorithm
//	单分片表分片算法		SingleKeyTableShardingAlgorithm
//	多分片键数据源分片算法	MultipleKeyDatabaseShardingAlgorithm
//	多分片表分片算法		MultipleKeyTableShardingAlgorithm
	
	
//	单分片建算法需要实现三个方法，下面以单分片建数据源分片算法举例:
//
//		@Override 
//		public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) 
//
//		@Override 
//		public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) 
//	
//		@Override 
//		public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue)
//	
//		这三种算法作用如下 - doEqualSharding在WHERE使用=作为条件分片键。算法中使用shardingValue.getValue()获取等=后的值
//					-doInSharding在WHERE使用IN作为条件分片键。算法中使用shardingValue.getValues()获取IN后的值 
//					-doBetweenSharding在WHERE使用BETWEEN作为条件分片键。算法中使用shardingValue.getValueRange()获取BETWEEN后的值
//	
//   -----------------------------------------------------------------------------------------------------------------------
//	
//	 多分片建算法适用于比较复杂的场景，为了提高灵活性故只提供了一个方法实现
//
//		public Collection<String> doSharding(final Collection<String> availableTargetNames,final Collection<ShardingValue<?>> shardingValues)
//
//		算法实现的时候根据shardingValue.getType来获取条件是= .IN还是between
//
//	举个例：
	
	public static void main(String[] args) {
		SpringApplication.run(DangdangApplication.class, args);
	}

}
