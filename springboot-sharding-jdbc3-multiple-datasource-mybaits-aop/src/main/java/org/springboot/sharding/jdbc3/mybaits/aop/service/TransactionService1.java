package org.springboot.sharding.jdbc3.mybaits.aop.service;

import org.springboot.sharding.jdbc3.mybaits.aop.bean.TestBean;
import org.springboot.sharding.jdbc3.mybaits.aop.dao.TransactionDao1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService1 {
	
	@Autowired
	private TransactionDao1 ts1;

	@Transactional
	public void test01_saveTestBean(TestBean t) {
		ts1.save(t);
	}

}
