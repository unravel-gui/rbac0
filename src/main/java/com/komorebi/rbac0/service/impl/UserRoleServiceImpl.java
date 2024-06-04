package com.komorebi.rbac0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komorebi.rbac0.mapper.RoleMapper;
import com.komorebi.rbac0.model.Permission;
import com.komorebi.rbac0.model.PtypeMenu;
import com.komorebi.rbac0.model.Role;
import com.komorebi.rbac0.model.UserRole;
import com.komorebi.rbac0.service.UserRoleService;
import com.komorebi.rbac0.mapper.UserRoleMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author HP
* @description 针对表【j_user_role】的数据库操作Service实现
* @createDate 2024-06-01 15:42:00
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Integer delByUids(List<Integer> ids) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserRole::getUid,ids);
        return userRoleMapper.delete(wrapper);
    }

    @Override
    public Boolean assignRole(Integer uid, List<Integer> include) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        // 设置条件：id 等于指定的 id
        wrapper.eq(UserRole::getUid, uid);
        userRoleMapper.delete(wrapper);
        // 批量插入
        List<UserRole> urs = new ArrayList<>();
        for (Integer rid : include) {
            Role r = roleMapper.selectById(rid);
            if (r==null){
                continue;
            }
            UserRole ur = new UserRole();
            ur.setUid(uid);
            ur.setRid(rid);
            urs.add(ur);
        }
        boolean b = saveBatch(urs);
        return b;
    }

    @Override
    public Integer delByRids(List<Integer> ids) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserRole::getRid,ids);
        return userRoleMapper.delete(wrapper);
    }
}




