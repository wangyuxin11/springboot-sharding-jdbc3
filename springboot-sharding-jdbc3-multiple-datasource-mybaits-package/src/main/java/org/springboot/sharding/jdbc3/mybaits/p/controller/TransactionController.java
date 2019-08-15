package org.springboot.sharding.jdbc3.mybaits.p.controller;

import java.util.UUID;

import org.springboot.sharding.jdbc3.mybaits.p.bean.TeachersBean;
import org.springboot.sharding.jdbc3.mybaits.p.bean.TestBean;
import org.springboot.sharding.jdbc3.mybaits.p.service.TransactionService1;
import org.springboot.sharding.jdbc3.mybaits.p.service.TransactionService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多数据源事务测试
 * 
 * @author 
 *
 */
@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService1 ts1;
	
	@Autowired
	private TransactionService2 ts2;

	
	/*
	 * http://localhost:8089/savetest.do
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
	 * http://localhost:8089/saveteacher.do
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
	/**
	 * 结果是一个插入进去了，属于非正常现象
	 * 
	 * http://localhost:8089/test.do
	 * 
	 * @return
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

	/**
	 * 结果是两个都没法插入---属于正常现象
	 * 
	 * http://localhost:8089/test2.do
	 * 
	 * @return
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

	/**
	 * 结果是一个插入进去了，属于非正常现象
	 * 
	 * http://localhost:8089/test3.do
	 * 
	 * @return
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
}
