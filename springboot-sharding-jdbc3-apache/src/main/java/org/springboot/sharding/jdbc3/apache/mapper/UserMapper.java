package org.springboot.sharding.jdbc3.apache.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.sharding.jdbc3.apache.entity.User;

@Mapper
public interface UserMapper {
    /**
     * 保存
     */
    void save(User user);
 
    /**
     * 查询
     * @param ids
     * @return
    List<User> getByIds(@Param("ids") List<Long> ids);
     */

    /**
     * 查询
     * 
     * @param id
     * @return
     */
	User getById(long id);
}
