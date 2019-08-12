package org.springboot.sharding.jdbc3.dangdang.shardingalgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springboot.sharding.jdbc3.dangdang.config.Database0Config;
import org.springboot.sharding.jdbc3.dangdang.config.Database1Config;
import org.springboot.sharding.jdbc3.dangdang.config.Database2Config;
import org.springboot.sharding.jdbc3.dangdang.config.Database3Config;
import org.springboot.sharding.jdbc3.dangdang.config.Database4Config;
import org.springboot.sharding.jdbc3.dangdang.config.Database5Config;
import org.springboot.sharding.jdbc3.dangdang.config.Database6Config;
import org.springboot.sharding.jdbc3.dangdang.config.Database7Config;
import org.springboot.sharding.jdbc3.dangdang.config.Database8Config;
import org.springboot.sharding.jdbc3.dangdang.config.Database9Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

@Component
public class AccDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

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

	@Override
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
		String databaseName = null;

		Integer value = shardingValue.getValue();
		Integer m = value % 10;
		switch (m) {
		case 0:
			databaseName = database0Config.getDatabaseName();
			break;
		case 1:
			databaseName = database1Config.getDatabaseName();
			break;
		case 2:
			databaseName = database2Config.getDatabaseName();
			break;
		case 3:
			databaseName = database3Config.getDatabaseName();
			break;
		case 4:
			databaseName = database4Config.getDatabaseName();
			break;
		case 5:
			databaseName = database5Config.getDatabaseName();
			break;
		case 6:
			databaseName = database6Config.getDatabaseName();
			break;
		case 7:
			databaseName = database7Config.getDatabaseName();
			break;
		case 8:
			databaseName = database8Config.getDatabaseName();
			break;
		case 9:
			databaseName = database9Config.getDatabaseName();
			break;
		}
		
		System.err.println("-------> AccDatabaseShardingAlgorithm.doEqualSharding - databaseName=" + databaseName);
		
		return databaseName;
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		
		String databaseName = null;
		for (Integer value : shardingValue.getValues()) {
			Integer m = value % 10;
			switch (m) {
			case 0:
				databaseName = database0Config.getDatabaseName();
				break;
			case 1:
				databaseName = database1Config.getDatabaseName();
				break;
			case 2:
				databaseName = database2Config.getDatabaseName();
				break;
			case 3:
				databaseName = database3Config.getDatabaseName();
				break;
			case 4:
				databaseName = database4Config.getDatabaseName();
				break;
			case 5:
				databaseName = database5Config.getDatabaseName();
				break;
			case 6:
				databaseName = database6Config.getDatabaseName();
				break;
			case 7:
				databaseName = database7Config.getDatabaseName();
				break;
			case 8:
				databaseName = database8Config.getDatabaseName();
				break;
			case 9:
				databaseName = database9Config.getDatabaseName();
				break;
			}
			result.add(databaseName); 
			System.err.println("-------> DatabaseShardingAlgorithm.doInSharding - databaseName=" + databaseName);
		}
		return result;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		Range<Integer> range = shardingValue.getValueRange();
		
		String databaseName = null;
		for (Integer value = range.lowerEndpoint(); value <= range.upperEndpoint(); value++) {
			Integer m = value % 10;
			switch (m) {
			case 0:
				databaseName = database0Config.getDatabaseName();
				break;
			case 1:
				databaseName = database1Config.getDatabaseName();
				break;
			case 2:
				databaseName = database2Config.getDatabaseName();
				break;
			case 3:
				databaseName = database3Config.getDatabaseName();
				break;
			case 4:
				databaseName = database4Config.getDatabaseName();
				break;
			case 5:
				databaseName = database5Config.getDatabaseName();
				break;
			case 6:
				databaseName = database6Config.getDatabaseName();
				break;
			case 7:
				databaseName = database7Config.getDatabaseName();
				break;
			case 8:
				databaseName = database8Config.getDatabaseName();
				break;
			case 9:
				databaseName = database9Config.getDatabaseName();
				break;
			}
			System.err.println("-------> AccDatabaseShardingAlgorithm.doBetweenSharding - databaseName=" + databaseName);
			result.add(databaseName);
		}
		return result;
	}

}
