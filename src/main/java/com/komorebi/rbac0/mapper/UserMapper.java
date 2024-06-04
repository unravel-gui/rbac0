package com.komorebi.rbac0.mapper;

import com.komorebi.rbac0.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author HP
* @description 针对表【j_user】的数据库操作Mapper
* @createDate 2024-06-02 15:55:37
* @Entity com.komorebi.rbac0.model.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getUserByRole(@Param("rid") Integer rid);
}




