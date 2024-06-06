package com.komorebi.rbac0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komorebi.rbac0.mapper.PtypeMenuMapper;
import com.komorebi.rbac0.model.Menu;
import com.komorebi.rbac0.model.PtypeMenu;
import com.komorebi.rbac0.service.MenuService;
import com.komorebi.rbac0.mapper.MenuMapper;
import com.komorebi.rbac0.service.PtypeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【j_menu】的数据库操作Service实现
* @createDate 2024-06-01 15:37:39
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements com.komorebi.rbac0.service.MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> getMenuByPtype(Integer tid) {
        List<Menu> mList = menuMapper.getMenuByPtype(tid);
        return mList;
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> mList = menuMapper.selectList(null);
        return mList;
    }

    @Override
    public Boolean delMenu(List<Integer> mids) {
        // 删除菜单
        int resRows = menuMapper.deleteBatchIds(mids);
        return resRows>0;
    }

    @Override
    public List<Menu> getMenuByUid(Integer uid) {
        List<Menu> mList = menuMapper.getMenuByUid(uid);
        return mList;
    }
}




