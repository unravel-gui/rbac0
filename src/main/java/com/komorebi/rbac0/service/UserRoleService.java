package com.komorebi.rbac0.service;

import com.komorebi.rbac0.model.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【j_user_role】的数据库操作Service
* @createDate 2024-06-01 15:42:00
*/
public interface UserRoleService extends IService<UserRole> {

    Integer delByUids(List<Integer> ids);

    Boolean assignRole(Integer uid, List<Integer> include_role);

    Integer delByRids(List<Integer> ids);
}
