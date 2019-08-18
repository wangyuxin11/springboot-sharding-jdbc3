package org.springboot.sharding.jdbc3.mybaits.aop.mapper;

import org.springboot.sharding.jdbc3.mybaits.aop.bean.TestBean;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMapping1 {

	void save(TestBean t);

}
