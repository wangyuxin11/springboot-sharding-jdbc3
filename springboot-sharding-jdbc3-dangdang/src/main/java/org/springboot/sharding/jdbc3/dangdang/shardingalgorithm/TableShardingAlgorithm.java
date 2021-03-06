package org.springboot.sharding.jdbc3.dangdang.shardingalgorithm;

/*
	分表和分库类似，无非就是实现的类不一样，实现了SingleKeyTableShardingAlgorithm类，策略使用值奇偶分表，
	分表算法类TableShardingAlgorithm如代码清单所示。
 */
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 这里使用的都是单键分片策略 示例分表策略是： GoodsType为奇数使用goods_1表 GoodsType为偶数使用goods_0表
 */
@Component
public class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

	@Override
	public String doEqualSharding(final Collection<String> tableNames, final ShardingValue<Long> shardingValue) {
		for (String each : tableNames) {
			if (each.endsWith(shardingValue.getValue() % 2 + "")) {
				System.err.println("---> TableShardingAlgorithm.doEqualSharding : tableName=" + each);
				return each;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(final Collection<String> tableNames,
			final ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(tableNames.size());
		for (Long value : shardingValue.getValues()) {
			for (String tableName : tableNames) {
				if (tableName.endsWith(value % 2 + "")) {
					System.err.println("---> TableShardingAlgorithm.doInSharding : tableName=" + tableName);
					result.add(tableName);
				}
			}
		}
		return result;
	}

	@Override
	public Collection<String> doBetweenSharding(final Collection<String> tableNames,
			final ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(tableNames.size());
		Range<Long> range = shardingValue.getValueRange();
		for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
			for (String each : tableNames) {
				if (each.endsWith(i % 2 + "")) {
					System.err.println("---> TableShardingAlgorithm.doBetweenSharding : tableName=" + each);
					result.add(each);
				}
			}
		}
		return result;
	}
}