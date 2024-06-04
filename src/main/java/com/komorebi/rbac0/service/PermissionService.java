package com.komorebi.rbac0.service;

import com.komorebi.rbac0.model.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【j_permission】的数据库操作Service
* @createDate 2024-06-01 15:37:39
*/
public interface PermissionService extends IService<Permission> {
    Boolean checkPermissionName(String name);

    List<Permission> getPermissionByPtype(Integer tid);

}
