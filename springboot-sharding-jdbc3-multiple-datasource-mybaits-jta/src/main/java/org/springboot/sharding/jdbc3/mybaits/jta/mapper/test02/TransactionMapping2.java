package org.springboot.sharding.jdbc3.mybaits.jta.mapper.test02;

import org.springboot.sharding.jdbc3.mybaits.jta.bean.TeachersBean;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMapping2 {

	void save(TeachersBean t);

}
