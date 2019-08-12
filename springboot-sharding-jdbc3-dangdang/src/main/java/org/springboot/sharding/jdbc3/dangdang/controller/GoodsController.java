package org.springboot.sharding.jdbc3.dangdang.controller;

import java.util.ArrayList;
import java.util.List;

import org.springboot.sharding.jdbc3.dangdang.entity.Goods;
import org.springboot.sharding.jdbc3.dangdang.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;

/**
 * 

启动应用，在浏览器或HTTP请求工具访问http://localhost:8090/springboot-sharding-jdbc3-dangdang/save，如图所示，返回success。

接下来在测试一下查询方法，访问http://localhost:8090/springboot-sharding-jdbc3-dangdang/select，如图所示，可以看到插入数据没问题。

然后查看一下数据库，首先看database0，如图，每个表都有十条数据，如下所示。

接下来看database1，如下所示。

从上面几张图可以看出分库分表已经按照我们的策略来进行插入，至于其他几个测试这里就不做介绍了，无论是查询和删除都是可以成功的。

 */
@RestController
public class GoodsController {

	@Autowired
	private KeyGenerator keyGenerator;

	@Autowired
	private GoodsRepository goodsRepository;

	/**
	 * http://localhost:8090/springboot-sharding-jdbc3-dangdang/save
	 * @return
	 */
	@GetMapping("save")
	public String save() {
		for (int i = 1; i <= 40; i++) {
			Goods goods = new Goods();
			goods.setGoodsId((long) i);
			goods.setGoodsName("shangpin" + i);
			goods.setGoodsType((long) (i + 1));
			goodsRepository.save(goods);
		}
		return "success";
	}

	/**
	 * http://localhost:8090/springboot-sharding-jdbc3-dangdang/select
	 * @return
	 */
	@GetMapping("select")
	public String select() {
		return goodsRepository.findAll().toString();
	}

	@GetMapping("delete")
	public void delete() {
		goodsRepository.deleteAll();
	}

	@GetMapping("query1")
	public Object query1() {
		return goodsRepository.findAllByGoodsIdBetween(10L, 30L);
	}

	@GetMapping("query2")
	public Object query2() {
		List<Long> goodsIds = new ArrayList<>();
		goodsIds.add(10L);
		goodsIds.add(15L);
		goodsIds.add(20L);
		goodsIds.add(25L);
		return goodsRepository.findAllByGoodsIdIn(goodsIds);
	}
}