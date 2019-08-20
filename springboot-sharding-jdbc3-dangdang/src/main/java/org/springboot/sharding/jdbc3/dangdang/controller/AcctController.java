package org.springboot.sharding.jdbc3.dangdang.controller;

import java.util.UUID;

import org.springboot.sharding.jdbc3.dangdang.entity.Acct;
import org.springboot.sharding.jdbc3.dangdang.repository.AcctRepository;
import org.springboot.sharding.jdbc3.dangdang.service.UidGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;

@RestController
public class AcctController {


	@Autowired
	private KeyGenerator keyGenerator;

	@Autowired
	private AcctRepository acctRepository;

    @Autowired
    private UidGenService uidGenService;
    
    
	/**
	 * http://localhost:8090/springboot-sharding-jdbc3-dangdang/acct/save
	 * @return
	 */
	@GetMapping("/acct/save")
	public String test() {
		StringBuffer accountBuffer = new StringBuffer();
		
		Acct acct = null;
		
		int i=1;
		while(i<2) {
			long acctId = uidGenService.getUid();
			
			//
			String userId = UUID.randomUUID().toString().replace("-", "");//用来生成数据库的主键id非常不错。。   
			int hashcode = userId.hashCode();
			
			System.err.println("acctId=" + acctId + "[acct_" + (acctId % 10) + "] | hashcode="
					+ String.valueOf(Math.abs(hashcode)) + "[database" + String.valueOf(Math.abs(hashcode) % 10) + "]");
			
			//分表依据 : DataSourceConfig  -> TableRule, ShardingRule
			acct = new Acct();
			acct.setAcctId(acctId);   //--> DataSourceConfig 计算 ‘分表’ 的依据
			acct.setUserId(userId);   //--> DataSourceConfig 计算 ‘分库’ 的依据
			acct.setHashcode(Math.abs(hashcode));
			
			Integer databaseNo = Math.abs(hashcode)%10;
			acct.setDatabaseNo(databaseNo);         //分库id --> 依据 acct_id 自增分布式雪花id来区分库，会造成一个现象就是：一阶段的数据都落在一个库里。
			
			Long tableNo = acctId%10;
			acct.setTableNo(tableNo.intValue());    //分表id -> user_id 相同的都落在一张表里。
			acctRepository.save(acct);
			
			System.err.println(acct.toString());
			//
			accountBuffer.append(String.valueOf(acctId)).append(" [")
				.append("database").append(Math.abs(hashcode)%10).append(" # ")
				.append("acct_").append(tableNo).append("]")
				.append(" - ");
			

			i++;
		}
		
		return accountBuffer.toString();
	}

	
	/**
	 * http://localhost:8090/springboot-sharding-jdbc3-dangdang/acct/queryAll
	 * @return
	 */
	@GetMapping("/acct/queryAll")
	public String select() {
		return acctRepository.findAll().toString();
	}
	
	//http://localhost:8090/springboot-sharding-jdbc3-dangdang/acct/query?acctIdStart=3526016692289462272&acctIdEnd=3526016692289462273
	@GetMapping("/acct/query")
	public Object query1(@RequestParam("acctIdStart") Long acctIdStart, 
			@RequestParam("acctIdEnd") Long acctIdEnd) {
		return acctRepository.findAllByAcctIdBetween(acctIdStart, acctIdEnd);   //find findAllByGoodsIdBetween(10L, 30L);
	}
	
	
	
//	@GetMapping("query2")
//	public Object query2() {
//		List<Long> goodsIds = new ArrayList<>();
//		goodsIds.add(10L);
//		goodsIds.add(15L);
//		goodsIds.add(20L);
//		goodsIds.add(25L);
//		return goodsRepository.findAllByGoodsIdIn(goodsIds);
//	}
	
	

	/**
	 * http://localhost:8090/springboot-sharding-jdbc3-dangdang/acct/delete
	 * 
	 */
	@GetMapping("/acct/delete")
	public void delete() {
		acctRepository.deleteAll();
	}

	
	
	
	
	
}
