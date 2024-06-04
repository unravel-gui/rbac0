package com.komorebi.rbac0.service;

import com.komorebi.rbac0.model.PtypePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【j_ptype_permission】的数据库操作Service
* @createDate 2024-06-01 15:42:00
*/
public interface PtypePermissionService extends IService<PtypePermission> {
    Boolean assignPermission(Integer tid, List<Integer> include);

    Integer delByTids(List<Integer> tids);
}
