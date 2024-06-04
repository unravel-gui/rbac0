package com.komorebi.rbac0.service;

import com.komorebi.rbac0.model.RolePtype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【j_role_ptype】的数据库操作Service
* @createDate 2024-06-01 15:42:00
*/
public interface RolePtypeService extends IService<RolePtype> {

    Integer delByRids(List<Integer> ids);
    Boolean assignPtype(Integer rid, List<Integer> include);

    Integer delByTids(List<Integer> tids);
}
