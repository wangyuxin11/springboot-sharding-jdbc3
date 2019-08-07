package org.springboot.sharding.jdbc3.repository;


import java.util.List;

import org.springboot.sharding.jdbc3.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
 
/**
 * 
 * 
 * 
 * @author wangyx
 *
 */
public interface GoodsRepository extends JpaRepository<Goods, Long> {
 
    List<Goods> findAllByGoodsIdBetween(Long goodsId1,Long goodsId2);
 
    List<Goods> findAllByGoodsIdIn(List<Long> goodsIds);
}