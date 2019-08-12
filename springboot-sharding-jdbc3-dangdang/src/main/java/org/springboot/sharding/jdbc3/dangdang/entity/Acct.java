package org.springboot.sharding.jdbc3.dangdang.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 

/**
 * 
 * 
 * 
 * @author wangyx
 *
 */
@Entity
@Table(name="acct")
@Data
@ToString
public class Acct {
	
	@Id
	private Integer acctId;
	
	private String userId;
	
	private Integer hashcode;
	
	private Integer modValue;

	
}
