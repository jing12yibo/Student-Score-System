package com.fc.service;

import com.fc.entity.TUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TUserService {
    List<TUser> getUser(TUser user);
    /**
     * 分页查询用户
     * @param page 页码
     * @param size 数量
     * @return
     */
    PageInfo<TUser> getUsersLimit(int page, int size);
}
