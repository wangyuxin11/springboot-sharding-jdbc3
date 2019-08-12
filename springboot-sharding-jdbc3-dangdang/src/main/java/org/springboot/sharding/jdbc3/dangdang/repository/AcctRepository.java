package org.springboot.sharding.jdbc3.dangdang.repository;

import java.util.List;

import org.springboot.sharding.jdbc3.dangdang.entity.Acct;
import org.springframework.data.jpa.repository.JpaRepository;
 
/**
 * 
 * 
 * 
 * @author wangyx
 *
 */
public interface AcctRepository extends JpaRepository<Acct, Integer> {
 
    List<Acct> findAllByAcctIdBetween(Integer acctId1, Integer acctId2);
 
    List<Acct> findAllByAcctIdIn(List<Integer> acctIds);
}