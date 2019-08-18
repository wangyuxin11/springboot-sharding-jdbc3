package org.springboot.sharding.jdbc3.mybaits.aop.service;

import org.springboot.sharding.jdbc3.mybaits.aop.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.aop.dao.TransactionDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService2 {
	
	@Autowired
	private TransactionDao2 ts2;

	@Transactional
	public void test02_saveTeachersBean(TeachersBean t) {
		ts2.save(t);
	}

}
