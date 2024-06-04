package com.komorebi.rbac0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komorebi.rbac0.mapper.PtypeMapper;
import com.komorebi.rbac0.model.Ptype;
import com.komorebi.rbac0.model.RolePtype;
import com.komorebi.rbac0.service.RolePtypeService;
import com.komorebi.rbac0.mapper.RolePtypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author HP
* @description 针对表【j_role_ptype】的数据库操作Service实现
* @createDate 2024-06-01 15:42:00
*/
@Service
public class RolePtypeServiceImpl extends ServiceImpl<RolePtypeMapper, RolePtype>
    implements RolePtypeService{
    @Autowired
    private RolePtypeMapper rolePtypeMapper;
    @Autowired
    private PtypeMapper ptypeMapper;
    
    @Override
    public Integer delByRids(List<Integer> ids) {
        LambdaQueryWrapper<RolePtype> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(RolePtype::getRid,ids);
        return rolePtypeMapper.delete(wrapper);
    }

    @Override
    public Boolean assignPtype(Integer rid, List<Integer> include) {
        // 删除当前角色所绑定的资源类型
        LambdaQueryWrapper<RolePtype> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePtype::getRid,rid);
        rolePtypeMapper.delete(wrapper);
        // 新增当前角色绑定的类型
        List<RolePtype> rps = new ArrayList<>();
        for (Integer tid:include) {
            Ptype p = ptypeMapper.selectById(tid);
            if (p==null){
                continue;
            }
            RolePtype rp = new RolePtype();
            rp.setRid(rid);
            rp.setTid(tid);
            rps.add(rp);
        }
        // 批量插入
        boolean res = saveBatch(rps);

        // 返回是否插入成功
        return res;
    }

    @Override
    public Integer delByTids(List<Integer> tids) {
        LambdaQueryWrapper<RolePtype> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(RolePtype::getTid,tids);
        return rolePtypeMapper.delete(wrapper);
    }
}




