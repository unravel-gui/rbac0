package com.komorebi.rbac0.service;

import com.komorebi.rbac0.model.DTO.UserQuery;
import com.komorebi.rbac0.model.Menu;
import com.komorebi.rbac0.model.Permission;
import com.komorebi.rbac0.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【j_user】的数据库操作Service
* @createDate 2024-06-02 15:55:37
*/
public interface UserService extends IService<User> {
    Boolean checkUsername(String username);
    Boolean checkUid(Integer uid);
    Boolean checkOpenId(Integer openId);

    Boolean checkLogin(String username, String password);

    User getUserByUsername(String username);

    Boolean delUser(List<Integer> ids);

    List<User> getUserByRole(Integer rid);

    Boolean activeUser(Integer uid, Boolean b);

    List<User> queryUser(UserQuery req);


    List<Menu> getUserMenu(Integer uid);
}
