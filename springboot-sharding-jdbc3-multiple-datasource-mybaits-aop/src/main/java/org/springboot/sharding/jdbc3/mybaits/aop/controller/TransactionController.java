package org.springboot.sharding.jdbc3.mybaits.aop.controller;

import java.util.UUID;

import org.springboot.sharding.jdbc3.mybaits.aop.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.aop.bean.TestBean;
import org.springboot.sharding.jdbc3.mybaits.aop.service.TransactionService1;
import org.springboot.sharding.jdbc3.mybaits.aop.service.TransactionService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多数据源事务测试
 * 
 *
 */
@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService1 ts1;
	
	@Autowired
	private TransactionService2 ts2;
	
	
	
	
	/**
	 * http://localhost:8092/savetest.do
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("/savetest.do")
	public String savetest() {
		TestBean tb = new TestBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setScore(70);
		tb.setClassid("1");
		tb.setUserid("a");
		ts1.test01_saveTestBean(tb);
		return "success";
	}

	
	/**
	 * http://localhost:8092/saveteacher.do
	 * 
	 * @return
	 */
	@RequestMapping("/saveteacher.do")
	public String saveteacher() {
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		ts2.test02_saveTeachersBean(tb);
		return "success";
	}

	
}
