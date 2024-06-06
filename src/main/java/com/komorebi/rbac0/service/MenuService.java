package com.komorebi.rbac0.service;

import com.komorebi.rbac0.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【j_menu】的数据库操作Service
* @createDate 2024-06-01 15:37:39
*/
public interface MenuService extends IService<Menu> {
    List<Menu> getMenuByPtype(Integer tid);
    List<Menu> getAllMenu();
    Boolean delMenu(List<Integer> mid);

    List<Menu> getMenuByUid(Integer uid);
}
