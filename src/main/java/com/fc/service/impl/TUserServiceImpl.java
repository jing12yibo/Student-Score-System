package com.fc.service.impl;

import com.fc.dao.TUserMapper;
import com.fc.entity.TUser;
import com.fc.service.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserMapper userMapper;

    @Override
    public List<TUser> getUser(TUser user) {
        return userMapper.getUser(user);
    }

    @Override
    public PageInfo<TUser> getUsersLimit(int page, int size) {
        //首先开启PageHelper的分页
        PageHelper.startPage(page, size);
        //查询分页信息 调用方式与普通方式一致
        List<TUser> list=userMapper.getUser(new TUser());
        //生成PageInfo对象
        PageInfo<TUser> pageInfo=new PageInfo<TUser>(list);
        return pageInfo;
    }
}
