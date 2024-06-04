package com.komorebi.rbac0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komorebi.rbac0.mapper.PermissionMapper;
import com.komorebi.rbac0.model.Menu;
import com.komorebi.rbac0.model.Permission;
import com.komorebi.rbac0.model.PtypeMenu;
import com.komorebi.rbac0.model.PtypePermission;
import com.komorebi.rbac0.service.PtypePermissionService;
import com.komorebi.rbac0.mapper.PtypePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author HP
* @description 针对表【j_ptype_permission】的数据库操作Service实现
* @createDate 2024-06-01 15:42:00
*/
@Service
public class PtypePermissionServiceImpl extends ServiceImpl<PtypePermissionMapper, PtypePermission>
    implements PtypePermissionService{
    @Autowired
    private PtypePermissionMapper ptypePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public Boolean assignPermission(Integer tid, List<Integer> include) {
        // 先删除
        LambdaQueryWrapper<PtypePermission> wrapper = new LambdaQueryWrapper<>();
        // 设置条件：id 等于指定的 id
        wrapper.eq(PtypePermission::getTid, tid);
        ptypePermissionMapper.delete(wrapper);
        List<PtypePermission> pps = new ArrayList<>();
        for (Integer pid : include) {
            Permission p = permissionMapper.selectById(pid);
            if (p==null){
                continue;
            }
            PtypePermission ptypePermission = new PtypePermission();
            ptypePermission.setTid(tid);
            ptypePermission.setPid(pid);
            pps.add(ptypePermission);
        }
        boolean b = saveBatch(pps);
        return b;
    }

    @Override
    public Integer delByTids(List<Integer> tids) {
        // 删除菜单与资源类型之间的联系
        LambdaQueryWrapper<PtypePermission> wrapper = new LambdaQueryWrapper<>();
        // 设置条件：id 等于指定的 id
        wrapper.in(PtypePermission::getTid, tids);
        // 执行删除操作
        int resRows = ptypePermissionMapper.delete(wrapper);
        return resRows;
    }
}




