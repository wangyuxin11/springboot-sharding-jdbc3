package org.springboot.sharding.jdbc3.apache.examples2.sharingalgorithm;

import java.util.Collection;

import com.alibaba.fastjson.JSON;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**
 * @program:
 * @description:
 */
public class IdSharingAlgorithm implements PreciseShardingAlgorithm<Long> {

	@Override
	public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {

		System.out.println("collection: " + JSON.toJSONString(collection) + " ,preciseShardingValue: "
				+ JSON.toJSONString(preciseShardingValue));

		Long id = preciseShardingValue.getValue();
		
		for (String name : collection) {
			if (name.endsWith(id % collection.size() + "")) {
				System.out.println("return name: " + name);
				return name;
			}
		}
		
		throw new IllegalArgumentException();
	}
	
}
