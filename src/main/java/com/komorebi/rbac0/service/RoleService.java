package com.komorebi.rbac0.service;

import com.komorebi.rbac0.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【j_role】的数据库操作Service
* @createDate 2024-06-01 15:37:39
*/
public interface RoleService extends IService<Role> {
    Boolean checkRoleName(String name);
    Boolean checkRole(Integer rid);

    Integer delRole(List<Integer> ids);
    List<Role> getRoleByUser(Integer uid);
}
