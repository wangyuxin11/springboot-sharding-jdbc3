package org.springboot.sharding.jdbc3.mybaits.jta.controller;

import java.util.UUID;

import org.springboot.sharding.jdbc3.mybaits.jta.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.jta.bean.TestBean;
import org.springboot.sharding.jdbc3.mybaits.jta.service.TransactionService1;
import org.springboot.sharding.jdbc3.mybaits.jta.service.TransactionService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多数据源事务测试 配置上jta-atomikos 之后事务变得正常 无论是test、test2还是test3，两个对象都是存不进去的
 * 
 * @author acer
 *
 */
@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService1 ts1;
	
	@Autowired
	private TransactionService2 ts2;
	
	
	/*
	 * http://localhost:8091/savetest.do
	 * 
	 * 1\cs_test表 保存一条
	 * 
	 */
	@RequestMapping("/savetest.do")
	public String savetest() {
		TestBean tb = new TestBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setScore(70);
		tb.setClassid("1");
		tb.setUserid("a");
		ts1.savetestBean(tb);
		return "success";
	}

	/*
	 * http://localhost:8091/saveteacher.do
	 * 
	 * 1\cs_teacher表 保存一条
	 * 
	 */
	@RequestMapping("/saveteacher.do")
	public String saveteacher() {
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		ts2.saveTeacher(tb);
		return "success";
	}

	// ########################开始事务测试##########################
	/*
	 * http://localhost:8091/test.do
	 * 
	 * 1\cs_teacher表 保存一条
	 * 2\异常回滚
	 * 3\cs_test表 保存一条
	 * 
	 * 结果：全部回滚
	 * 
	 */
	@RequestMapping("/test.do")
	public String test() {
		TestBean tb = new TestBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setScore(70);
		tb.setClassid("1");
		tb.setUserid("a");
		ts1.savetestBean2(tb);
		return "success";
	}

	/*
	 * http://localhost:8091/test2.do
	 * 
	 * 1\异常回滚
	 * 2\cs_teacher表 保存一条
	 * 3\cs_test表 保存一条
	 * 
	 * 结果：全部回滚
	 * 
	 */
	@RequestMapping("/test2.do")
	public String test2() {
		TestBean tb = new TestBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setScore(70);
		tb.setClassid("1");
		tb.setUserid("a");
		ts1.savetestBean3(tb);
		return "success";
	}

	/*
	 * http://localhost:8091/test3.do
	 * 
	 * 1\cs_teacher表 保存一条  : 直接注入数据源2的dao层就不受这个事务控制
	 * 2\异常
	 * 3\cs_test表 保存一条
	 * 
	 * 结果：全部回滚
	 * 
	 */
	@RequestMapping("/test3.do")
	public String test3() {
		TestBean tb = new TestBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setScore(70);
		tb.setClassid("1");
		tb.setUserid("a");
		ts1.savetestBean4(tb);
		return "success";
	}
	
	
	/*
	 * http://localhost:8091/test4.do
	 * 
	 * 1\cs_teacher表 保存一条  : 直接注入数据源2的dao层就不受这个事务控制
	 * 2\cs_test表 保存一条
	 * 3\异常
	 * 
	 * 结果：全部回滚
	 * 
	 */
	@RequestMapping("/test4.do")
	public String test4() {
		TestBean tb = new TestBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setScore(70);
		tb.setClassid("1");
		tb.setUserid("a");
		ts1.savetestBean5(tb);
		return "success";
	}
	
	
	
}
