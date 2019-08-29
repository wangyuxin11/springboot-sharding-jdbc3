package org.springboot.sharding.jdbc3.apache.examples2.sharingalgorithm;

import java.util.Collection;

import com.alibaba.fastjson.JSON;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import io.shardingsphere.api.algorithm.masterslave.MasterSlaveLoadBalanceAlgorithm;



/**
 * StandardShardingStrategy只支持单分片键，提供PreciseShardingAlgorithm和RangeShardingAlgorithm两个分片算法。
 * 
 *     PreciseShardingAlgorithm是必选的，用于处理=和IN的分片。 
 * 
 *	Precise 精确
 *	Sharding 分片 
 *  Algorithm 算法
 */
public class IdSharingAlgorithm implements PreciseShardingAlgorithm<Long> {

	
	
	/**
	 * 	shardingValue其实就是分片项，也就是比如order_id、user_id等等字段值，
	 * 
	 * 	availableTargetNames就是所谓的实际数据库表节点。这边遍历的也是实际节点，当分片项（或分片字段）满足一定的条件时，返回实际数据库表节点，用于组装sql。
	 * 
	 *	 总的来说，分片算法这块其实根据自己的业务需求自己进行扩展的，总的来说还是要根据实际的机器部署情况来。另外读写分离这块是否需要进行扩展，也是看后续的需要。
	 * 
	 */
	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> preciseShardingValue) {
		System.out.println("IdSharingAlgorithm --> 打印 collection: " + JSON.toJSONString(availableTargetNames) 
				+ " ,preciseShardingValue: " + JSON.toJSONString(preciseShardingValue));
		
		Long id = preciseShardingValue.getValue();
		
		for (String name : availableTargetNames) {
			if (name.endsWith(id % availableTargetNames.size() + "")) {
				System.out.println("return name: " + name);
				return name;
			}
		}
		
		throw new IllegalArgumentException();
	}
	
}
