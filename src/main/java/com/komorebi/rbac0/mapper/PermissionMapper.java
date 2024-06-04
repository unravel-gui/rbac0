package com.komorebi.rbac0.mapper;

import com.komorebi.rbac0.model.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author HP
* @description 针对表【j_permission】的数据库操作Mapper
* @createDate 2024-06-01 15:37:39
* @Entity com.komorebi.rbac0.model.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getPermissionByPtype(@Param("tid") Integer tid);
    Boolean upsert(@Param("p") Permission p);
}




