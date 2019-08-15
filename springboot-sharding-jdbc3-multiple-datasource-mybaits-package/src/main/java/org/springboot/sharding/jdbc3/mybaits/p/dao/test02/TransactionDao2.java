package org.springboot.sharding.jdbc3.mybaits.p.dao.test02;

import org.springboot.sharding.jdbc3.mybaits.p.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.p.mapper.test02.TransactionMapping2;
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
