package com.komorebi.rbac0.mapper;

import com.komorebi.rbac0.model.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author HP
* @description 针对表【j_menu】的数据库操作Mapper
* @createDate 2024-06-01 15:37:39
* @Entity com.komorebi.rbac0.model.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getMenuByPtype(@Param("tid")Integer tid);

    List<Menu> getMenuByUid(@Param("uid") Integer uid);
}




