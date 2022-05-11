package com.fc.dao;

import com.fc.entity.TUser;
import com.fc.entity.TUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper //标识为mybatis数据层接口
public interface TUserMapper {
    /**
     *全量查询用户
     * @param user
     * @return
     */
    List<TUser> getUser(TUser user);
}