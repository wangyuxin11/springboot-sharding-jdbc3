package org.springboot.sharding.jdbc3;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.sharding.jdbc3.apache.ShardingJdbcAplication;
import org.springboot.sharding.jdbc3.apache.entity.User;
import org.springboot.sharding.jdbc3.apache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.util.IdGenerator;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ShardingJdbcAplication.class)
@WebAppConfiguration
public class UserTest {

	@Autowired
	UserMapper userMapper;

//	@Resource
//	IdGenerator idGenerator;

	@Test
	public void save() {
		for (Integer i = 0; i < 100; i++) {
			User user = new User();
			user.setId(i.longValue());
			user.setName("wyx" + i);
			user.setCreateTime(new Date());
			user.setSex(i % 2);
			user.setPhone("12345678910");
			user.setEmail("123@qq.com");
			user.setPassword("123456");
			userMapper.save(user);
		}
	}

	@Test
	public void getByIds() {
		System.err.println("----->" + JSON.toJSONString(userMapper.getById(12L)));
	}

}
