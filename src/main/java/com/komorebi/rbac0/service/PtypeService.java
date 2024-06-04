package com.komorebi.rbac0.service;

import com.komorebi.rbac0.model.Ptype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【j_ptype】的数据库操作Service
* @createDate 2024-06-03 12:58:23
*/
public interface PtypeService extends IService<Ptype> {
    /**
     * 通过角色ID找到资源类型
     * @param rid
     * @return
     */
    List<Ptype> getPtypeByRole(Integer rid);

    /**
     * 批量删除资源类型
     * @param tids
     * @return
     */
    Boolean delPtype(List<Integer> tids);

    /**
     * 检查资源类型是否存在
     * @param tid
     * @return
     */
    Boolean checkPtype(Integer tid);

    /**
     * 检查资源类型名字是否重复
     * @param name
     * @return
     */
    Boolean checkPtypename(String name);
}
