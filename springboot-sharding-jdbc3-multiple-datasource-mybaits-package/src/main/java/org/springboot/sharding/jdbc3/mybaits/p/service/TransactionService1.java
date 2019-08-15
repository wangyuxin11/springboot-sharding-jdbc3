package org.springboot.sharding.jdbc3.mybaits.p.service;

import java.util.UUID;

import org.springboot.sharding.jdbc3.mybaits.p.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.p.bean.TestBean;
import org.springboot.sharding.jdbc3.mybaits.p.dao.test01.TransactionDao1;
import org.springboot.sharding.jdbc3.mybaits.p.dao.test02.TransactionDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService1 {
	
	@Autowired
	private TransactionDao1 ts1;
	
	@Autowired
	private TransactionService2 ts2;
	
	@Autowired
	private TransactionDao2 td2;
	

	@Transactional
	public void savetestBean(TestBean t) {
		ts1.save(t);
	}
	
	
	@Transactional
	public void savetestBean2(TestBean t) {
		
		// cs_teacher表可以插入
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		ts2.saveTeacher(tb);

		// TODO 故意报错，此处报错，上述cs_teacher表插入并不回滚
		int i = 1 / 0;
		ts1.save(t);
	}
	
	
	@Transactional
	public void savetestBean3(TestBean t) {

		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		ts2.saveTeacher2(tb);  //保存前先报错，未保存成功

		int i = 1 / 0; // 特意报错，但是全部回滚。正常现象
		ts1.save(t);
	}
	
	
	/**
	 * 直接注入数据源2的dao层就不受这个事务控制了
	 * 
	 * @param t
	 */
	@Transactional
	public void savetestBean4(TestBean t) {
		
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		td2.save(tb);
		
		int i = 1 / 0;
		ts1.save(t);
	}

}
