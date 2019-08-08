package org.springboot.sharding.jdbc3.apache.examples2.sharingalgorithm;

import java.util.ArrayList;
import java.util.Collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;

import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

public class IdRangeSharingAlgorithm implements RangeShardingAlgorithm<Long> {
 
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
 
        System.out.println("collection: " + JSON.toJSONString(collection) + " ,rangeShardingValue: "
                + JSON.toJSONString(rangeShardingValue));
 
        Collection<String> collect = new ArrayList<>();
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        for (Long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % collection.size() + "")) {
                    collect.add(each);
                }
            }
        }
        return collect;
    }
}
