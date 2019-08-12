package org.springboot.sharding.jdbc3.multiple.jpa.repository.datasource1;

import org.springboot.sharding.jdbc3.multiple.jpa.entity.datasource1.House;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 
 * @author wangyx
 *
 */
public interface HouseRepository extends JpaRepository<House,Integer> {
	
}