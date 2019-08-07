package org.springboot.sharding.jdbc3.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * 
 * @author wangyx
 *
 */
@Entity
@Table(name="goods")
@Data
public class Goods {
    @Id
    private Long goodsId;
 
    private String goodsName;
 
    private Long goodsType;
}