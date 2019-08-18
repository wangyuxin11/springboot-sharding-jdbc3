package org.springboot.sharding.jdbc3.mybaits.aop.dao;

import org.springboot.sharding.jdbc3.mybaits.aop.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.aop.mapper.TransactionMapping2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionDao2 {
	
	@Autowired
	private TransactionMapping2 tm2;

	public void save(TeachersBean t) {
		tm2.save(t);
	}

}
