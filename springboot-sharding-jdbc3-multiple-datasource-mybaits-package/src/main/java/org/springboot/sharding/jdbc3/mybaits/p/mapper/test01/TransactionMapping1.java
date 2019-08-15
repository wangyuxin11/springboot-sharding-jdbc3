package org.springboot.sharding.jdbc3.mybaits.p.mapper.test01;

import org.springboot.sharding.jdbc3.mybaits.p.bean.TestBean;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMapping1 {

	void save(TestBean t);

}
