package org.springboot.sharding.jdbc3.mybaits.aop.mapper;

import org.springboot.sharding.jdbc3.mybaits.aop.bean.TeachersBean;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMapping2 {

	void save(TeachersBean t);

}
