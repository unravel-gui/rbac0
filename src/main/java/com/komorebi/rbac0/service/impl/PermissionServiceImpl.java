package com.komorebi.rbac0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komorebi.rbac0.model.Permission;
import com.komorebi.rbac0.service.PermissionService;
import com.komorebi.rbac0.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【j_permission】的数据库操作Service实现
* @createDate 2024-06-01 15:37:39
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public Boolean checkPermissionName(String name) {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getName,name);
        return permissionMapper.exists(wrapper);
    }

    @Override
    public List<Permission> getPermissionByPtype(Integer tid) {
        return permissionMapper.getPermissionByPtype(tid);
    }
}




