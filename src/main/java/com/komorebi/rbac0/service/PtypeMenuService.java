package com.komorebi.rbac0.service;

import com.komorebi.rbac0.model.PtypeMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【j_ptype_menu】的数据库操作Service
* @createDate 2024-06-01 15:42:00
*/
public interface PtypeMenuService extends IService<PtypeMenu> {
    Integer delByMids(List<Integer> mids);
    Integer delByTids(List<Integer> tids);
    Boolean assignMenu(Integer tid,List<Integer> include);
}
