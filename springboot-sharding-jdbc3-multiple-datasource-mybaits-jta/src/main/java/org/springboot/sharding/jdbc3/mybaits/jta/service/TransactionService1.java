package org.springboot.sharding.jdbc3.mybaits.jta.service;

import java.util.UUID;

import org.springboot.sharding.jdbc3.mybaits.jta.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.jta.bean.TestBean;
import org.springboot.sharding.jdbc3.mybaits.jta.dao.test01.TransactionDao1;
import org.springboot.sharding.jdbc3.mybaits.jta.dao.test02.TransactionDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService1 {
	
	@Autowired
	private TransactionDao1 td1;

	@Autowired
	private TransactionDao2 td2;
	
	@Autowired
	private TransactionService2 ts2;
	
	
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param t
	 */
	@Transactional
	public void savetestBean(TestBean t) {
		td1.save(t);
	}

	
	/**
	 * ts2, td1
	 * 
	 * 
	 * @param t
	 */
	@Transactional
	public void savetestBean2(TestBean t) {
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		ts2.saveTeacher(tb);
		int i = 1 / 0;
		td1.save(t);
	}

	
	/**
	 * ts2, td1 
	 * 
	 * @param t
	 */
	@Transactional
	public void savetestBean3(TestBean t) {
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		ts2.saveTeacher2(tb);
		
		int i = 1 / 0;
		td1.save(t);
	}

	/**
	 * 直接注入数据源2的dao层就不受这个事务控制了
	 * 
	 * td2, td1
	 * 
	 * 1\cs_teacher 保存1条
	 * 2\异常
	 * 3\cs_test 保存1条
	 * 
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
		td1.save(t);
	}


	/**
	 * 
	 * 1\cs_teacher 保存1条
	 * 2\cs_test 保存1条
	 * 3\异常
	 * 
	 * 
	 * @param t
	 */
	@Transactional
	public void savetestBean5(TestBean t) {
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		td2.save(tb);
		
		td1.save(t);
		int i = 1 / 0;
	}

}
