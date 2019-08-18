package org.springboot.sharding.jdbc3.mybaits.aop.dao;

import org.springboot.sharding.jdbc3.mybaits.aop.bean.TestBean;
import org.springboot.sharding.jdbc3.mybaits.aop.mapper.TransactionMapping1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionDao1 {
	
	@Autowired
	private TransactionMapping1 tm1;

	public void save(TestBean t) {
		tm1.save(t);
	}

}
