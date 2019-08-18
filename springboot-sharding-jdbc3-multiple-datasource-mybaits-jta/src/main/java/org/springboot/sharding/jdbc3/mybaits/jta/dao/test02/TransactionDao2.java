package org.springboot.sharding.jdbc3.mybaits.jta.dao.test02;

import org.springboot.sharding.jdbc3.mybaits.jta.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.jta.mapper.test02.TransactionMapping2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionDao2 {
	
	@Autowired
	private TransactionMapping2 transactionMapping2;

	public void save(TeachersBean t) {
		transactionMapping2.save(t);
	}

}
