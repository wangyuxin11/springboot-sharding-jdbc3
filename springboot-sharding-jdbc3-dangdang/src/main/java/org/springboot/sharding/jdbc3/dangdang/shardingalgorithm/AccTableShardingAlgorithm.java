package org.springboot.sharding.jdbc3.dangdang.shardingalgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;


/**
 * 	单分片表分片算法	->	SingleKeyTableShardingAlgorithm
 * 
 * @author wangyx
 *
 */
@Component
public class AccTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

	@Override
	public String doEqualSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
		//Long acctId = shardingValue.getValue();
		Long acctIdMode = shardingValue.getValue() % 10;
		System.err.println("acctId : " + shardingValue.getValue() + " >> acct_" + String.valueOf(acctIdMode));
		
		for (String each : tableNames) {
//			if (each.endsWith(shardingValue.getValue() % 10 + "")) {
//				//return each;
//				tableName = each;
//			}
			if(each.equals("acct_" + String.valueOf(acctIdMode))) {
				System.err.println("---> tableName=" + each);
				return each;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> tableNames,
			ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(tableNames.size());
		for (Long value : shardingValue.getValues()) {
			for (String tableName : tableNames) {
				if (tableName.endsWith(value % 10 + "")) {
					System.err.println("---> doInSharding : tableName=" + tableName);
					result.add(tableName);
				}
			}
		}
		return result;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> tableNames,
			ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(tableNames.size());
		Range<Long> range = shardingValue.getValueRange();
		for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
			for (String each : tableNames) {
				if (each.endsWith(i % 10 + "")) {
					System.err.println("---> doBetweenSharding : tableName=" + each);
					result.add(each);
				}
			}
		}
		return result;
	}

}
