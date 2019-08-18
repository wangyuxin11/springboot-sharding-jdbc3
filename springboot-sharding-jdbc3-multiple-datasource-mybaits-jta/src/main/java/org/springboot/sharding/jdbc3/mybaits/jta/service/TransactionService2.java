package org.springboot.sharding.jdbc3.mybaits.jta.service;

import org.springboot.sharding.jdbc3.mybaits.jta.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.jta.dao.test02.TransactionDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService2 {
	
	@Autowired
	private TransactionDao2 td2;

	@Transactional
	public void saveTeacher(TeachersBean t) {
		td2.save(t);
	}

	@Transactional
	public void saveTeacher2(TeachersBean t) {
		int i = 1 / 0;
		td2.save(t);
	}
}
