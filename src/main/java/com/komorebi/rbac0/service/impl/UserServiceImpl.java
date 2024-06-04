package com.komorebi.rbac0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komorebi.rbac0.model.User;
import com.komorebi.rbac0.service.UserService;
import com.komorebi.rbac0.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【j_user】的数据库操作Service实现
* @createDate 2024-06-02 15:55:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean checkUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        return userMapper.exists(wrapper);
    }

    @Override
    public Boolean checkUid(Integer uid) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,uid);
        return userMapper.exists(wrapper);
    }

    @Override
    public Boolean checkOpenId(Integer openId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenId,openId);
        return userMapper.exists(wrapper);
    }

    @Override
    public Boolean checkLogin(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        wrapper.eq(User::getPassword,password);
        return userMapper.exists(wrapper);
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Boolean delUser(List<Integer> ids) {
        return userMapper.deleteBatchIds(ids)>0;
    }

    @Override
    public List<User> getUserByRole(Integer rid) {
        List<User> users = userMapper.getUserByRole(rid);
        return users;
    }

    @Override
    public Boolean activeUser(Integer uid, Boolean b) {
        User user = userMapper.selectById(uid);
        user.setIsActivce(b);
        return userMapper.updateById(user)>0;
    }
}




