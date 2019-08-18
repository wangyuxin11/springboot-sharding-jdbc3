package org.springboot.sharding.jdbc3.mybaits.aop.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springboot.sharding.jdbc3.mybaits.aop.datasource.DataSourceType.DataBaseType;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
	
	
	@Before("execution(* org.springboot.sharding.jdbc3.mybaits.aop.service..*.test01*(..))")
	public void setDataSource2test01() {
		System.err.println("test01业务");
		DataSourceType.setDataBaseType(DataBaseType.TEST01);
	}
	
	
	@Before("execution(* org.springboot.sharding.jdbc3.mybaits.aop.service..*.test02*(..))")
	public void setDataSource2test02() {
		System.err.println("test02业务");
		DataSourceType.setDataBaseType(DataBaseType.TEST02);
	}
	
	
}
