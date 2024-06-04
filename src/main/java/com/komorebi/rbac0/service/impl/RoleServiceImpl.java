package com.komorebi.rbac0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komorebi.rbac0.model.Role;
import com.komorebi.rbac0.service.RoleService;
import com.komorebi.rbac0.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【j_role】的数据库操作Service实现
* @createDate 2024-06-01 15:37:39
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Boolean checkRoleName(String name) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getRolename,name);
        Long count = roleMapper.selectCount(wrapper);
        return count>0;
    }

    @Override
    public Boolean checkRole(Integer rid) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getRid,rid);
        Long count = roleMapper.selectCount(wrapper);
        return count>0;
    }

    @Override
    public Integer delRole(List<Integer> ids) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Role::getRid,ids);
        return roleMapper.delete(wrapper);
    }

    @Override
    public List<Role> getRoleByUser(Integer uid) {
        return roleMapper.getRoleByUser(uid);
    }
}




