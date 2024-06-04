package com.komorebi.rbac0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komorebi.rbac0.mapper.MenuMapper;
import com.komorebi.rbac0.model.Menu;
import com.komorebi.rbac0.model.Ptype;
import com.komorebi.rbac0.model.PtypeMenu;
import com.komorebi.rbac0.service.PtypeMenuService;
import com.komorebi.rbac0.mapper.PtypeMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author HP
* @description 针对表【j_ptype_menu】的数据库操作Service实现
* @createDate 2024-06-01 15:42:00
*/
@Service
public class PtypeMenuServiceImpl extends ServiceImpl<PtypeMenuMapper, PtypeMenu>
    implements PtypeMenuService{

    @Autowired
    private PtypeMenuMapper ptypeMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Integer delByMids(List<Integer> mids) {
        // 删除菜单与资源类型之间的联系
        LambdaQueryWrapper<PtypeMenu> wrapper = new LambdaQueryWrapper<>();
        // 设置条件：id 等于指定的 id
        wrapper.in(PtypeMenu::getMid, mids);
        // 执行删除操作
        int resRows = ptypeMenuMapper.delete(wrapper);
        return resRows;
    }

    @Override
    public Integer delByTids(List<Integer> tids) {
        // 删除菜单与资源类型之间的联系
        LambdaQueryWrapper<PtypeMenu> wrapper = new LambdaQueryWrapper<>();
        // 设置条件：id 等于指定的 id
        wrapper.in(PtypeMenu::getTid, tids);
        // 执行删除操作
        int resRows = ptypeMenuMapper.delete(wrapper);
        return resRows;
    }

    @Override
    public Boolean assignMenu(Integer tid,List<Integer> include) {
        LambdaQueryWrapper<PtypeMenu> wrapper = new LambdaQueryWrapper<>();
        // 设置条件：id 等于指定的 id
        wrapper.eq(PtypeMenu::getTid, tid);
        ptypeMenuMapper.delete(wrapper);
        // 批量插入
        List<PtypeMenu> pms = new ArrayList<>();
        for (Integer mid : include) {
            Menu m = menuMapper.selectById(mid);
            if (m==null){
                continue;
            }
            PtypeMenu ptypeMenu = new PtypeMenu();
            ptypeMenu.setTid(tid);
            ptypeMenu.setMid(mid);
            pms.add(ptypeMenu);
        }
        boolean b = saveBatch(pms);
        return b;
    }
}




