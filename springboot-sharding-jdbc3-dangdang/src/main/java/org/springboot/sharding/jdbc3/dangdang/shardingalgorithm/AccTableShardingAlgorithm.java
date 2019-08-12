package org.springboot.sharding.jdbc3.dangdang.shardingalgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

@Component
public class AccTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {

	@Override
	public String doEqualSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
		for (String each : tableNames) {
			if (each.endsWith(shardingValue.getValue() % 10 + "")) {
				System.err.println("---> AccTableShardingAlgorithm.doEqualSharding : tableName=" + each);
				return each;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> tableNames,
			ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(tableNames.size());
		for (Integer value : shardingValue.getValues()) {
			for (String tableName : tableNames) {
				if (tableName.endsWith(value % 10 + "")) {
					System.err.println("---> AccTableShardingAlgorithm.doInSharding : tableName=" + tableName);
					result.add(tableName);
				}
			}
		}
		return result;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> tableNames,
			ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(tableNames.size());
		Range<Integer> range = shardingValue.getValueRange();
		for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
			for (String each : tableNames) {
				if (each.endsWith(i % 10 + "")) {
					System.err.println("---> AccTableShardingAlgorithm.doBetweenSharding : tableName=" + each);
					result.add(each);
				}
			}
		}
		return result;
	}

}
