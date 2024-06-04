package com.komorebi.rbac0.mapper;

import com.komorebi.rbac0.model.Ptype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author HP
* @description 针对表【j_ptype】的数据库操作Mapper
* @createDate 2024-06-03 12:58:22
* @Entity com.komorebi.rbac0.model.Ptype
*/
@Mapper
public interface PtypeMapper extends BaseMapper<Ptype> {
    List<Ptype> getPtypeByRole(@Param("rid") Integer rid);

}




