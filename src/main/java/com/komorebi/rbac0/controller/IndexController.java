package com.komorebi.rbac0.controller;

import com.komorebi.rbac0.common.utils.HashUtils;
import com.komorebi.rbac0.common.utils.JWTUtils;
import com.komorebi.rbac0.common.utils.Result;
import com.komorebi.rbac0.model.DTO.UserLogin;
import com.komorebi.rbac0.model.DTO.UserLoginOrRegisterResp;
import com.komorebi.rbac0.model.User;
import com.komorebi.rbac0.service.PtypeMenuService;
import com.komorebi.rbac0.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class IndexController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "普通用户注册",notes = "普通用户注册的描述")
    @PostMapping("/register")
    public Result register(@RequestBody User req) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        // TODO： 校验
        req.setUid(null);
        if(userService.checkUsername(req.getUsername())) {
            return Result.fail("用户名已存在");
        }
        // TODO:针对 open_id 的处理

        try{
            // 处理密码
            req.setPassword(HashUtils.sha256(req.getPassword()));
            boolean res = userService.save(req);
            if (!res){
                return Result.fail("register failed");
            }
            UserLoginOrRegisterResp ur = getUserLoginOrRegisterResp(req.getUsername());
            return Result.success(ur);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "普通用户登录",notes = "普通用户登录的描述")
    @PostMapping("/login")
    public Result login(@RequestBody UserLogin req) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        try{
            // 处理密码
            req.password=HashUtils.sha256(req.password);
            Boolean isLogin = userService.checkLogin(req.username,req.password);
            if (!isLogin) {
                return Result.fail("username or password wrong");
            }
            UserLoginOrRegisterResp ur = getUserLoginOrRegisterResp(req.username);
            return Result.success(ur);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }



    public UserLoginOrRegisterResp getUserLoginOrRegisterResp(String username){
        User user = userService.getUserByUsername(username);
        user.setPassword(null);
        String uidStr = String.valueOf(user.getUid());
        String jwt_token = JWTUtils.generateToken(uidStr);
        UserLoginOrRegisterResp ur = new UserLoginOrRegisterResp(user,jwt_token);
        return ur;
    }
}
