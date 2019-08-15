package org.springboot.sharding.jdbc3.mybaits.p.mapper.test02;

import org.springboot.sharding.jdbc3.mybaits.p.bean.TeachersBean;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMapping2 {

	void save(TeachersBean t);

}
