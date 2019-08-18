package org.springboot.sharding.jdbc3.mybaits.jta.dao.test01;

import org.springboot.sharding.jdbc3.mybaits.jta.bean.TestBean;
import org.springboot.sharding.jdbc3.mybaits.jta.mapper.test01.TransactionMapping1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionDao1 {
	
	@Autowired
	private TransactionMapping1 transactionMapping1;

	public void save(TestBean t) {
		transactionMapping1.save(t);
	}

}
