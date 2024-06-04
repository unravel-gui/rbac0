package com.komorebi.rbac0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komorebi.rbac0.model.Ptype;
import com.komorebi.rbac0.service.PtypeService;
import com.komorebi.rbac0.mapper.PtypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【j_ptype】的数据库操作Service实现
* @createDate 2024-06-03 12:58:23
*/
@Service
public class PtypeServiceImpl extends ServiceImpl<PtypeMapper, Ptype>
        implements PtypeService {
    @Autowired
    private PtypeMapper ptypeMapper;

    @Override
    public List<Ptype> getPtypeByRole(Integer rid) {
        List<Ptype> ptypes = ptypeMapper.getPtypeByRole(rid);
        return ptypes;
    }

    @Override
    public Boolean delPtype(List<Integer> tids) {
        // 删除菜单
        int resRows = ptypeMapper.deleteBatchIds(tids);
        return resRows>0;
    }

    @Override
    public Boolean checkPtype(Integer tid) {
        LambdaQueryWrapper<Ptype> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ptype::getTid,tid);
        return ptypeMapper.exists(wrapper);
    }

    @Override
    public Boolean checkPtypename(String name) {
        LambdaQueryWrapper<Ptype> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ptype::getName,name);
        return ptypeMapper.exists(wrapper);
    }
}




