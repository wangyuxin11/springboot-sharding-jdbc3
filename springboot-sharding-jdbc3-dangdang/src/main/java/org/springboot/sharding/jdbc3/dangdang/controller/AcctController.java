package org.springboot.sharding.jdbc3.dangdang.controller;

import java.util.UUID;

import org.springboot.sharding.jdbc3.dangdang.entity.Acct;
import org.springboot.sharding.jdbc3.dangdang.entity.Goods;
import org.springboot.sharding.jdbc3.dangdang.repository.AcctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.json.JSONParser;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
public class AcctController {


	@Autowired
	private KeyGenerator keyGenerator;

	@Autowired
	private AcctRepository acctRepository;

	
	/**
	 * http://localhost:8090/springboot-sharding-jdbc3-dangdang/test
	 * @return
	 */
	@GetMapping("test")
	public String test() {
		Acct acct = null;
		
		int i=1;
		while(i<11) {
			String userId = UUID.randomUUID().toString();//用来生成数据库的主键id非常不错。。   
			int hashcode = userId.hashCode();
			
			acct = new Acct();
			acct.setAcctId(i);
			acct.setUserId(userId);
			acct.setHashcode(Math.abs(hashcode));
			acct.setModValue(Math.abs(hashcode%10));
			acctRepository.save(acct);
			
			System.err.println(acct.toString());

			i++;
		}
		
		return "success";
	}

	
	
	
	
	
	
}
