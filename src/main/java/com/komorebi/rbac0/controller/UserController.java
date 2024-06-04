package com.komorebi.rbac0.controller;

import com.komorebi.rbac0.common.utils.HashUtils;
import com.komorebi.rbac0.common.utils.JWTUtils;
import com.komorebi.rbac0.common.utils.Result;
import com.komorebi.rbac0.model.DTO.*;
import com.komorebi.rbac0.model.User;
import com.komorebi.rbac0.service.UserRoleService;
import com.komorebi.rbac0.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "管理员登录",notes = "管理员登录的描述")
    @PostMapping("/login")
    public Result adminLogin(@RequestBody UserLogin req) {
        // 调用 service 层的方法将 user 对象插入到数据库中
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

    @ApiOperation(value = "添加用户",notes = "添加用户的描述")
    @PutMapping("/putUser")
    public Result<Boolean> addUser(@RequestBody User req) {
        // 调用 service 层的方法将 user 对象插入到数据库中
        // TODO： 校验
        req.setUid(null);
        if(userService.checkUsername(req.getUsername())) {
            return Result.fail("用户名已存在");
        }

        try{
            // 处理密码
            req.setPassword(HashUtils.sha256(req.getPassword()));
            boolean res = userService.save(req);
            if (!res){
                return Result.fail("register failed");
            }
            return Result.success(res);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "删除用户",notes = "删除用户的描述")
    @DeleteMapping("/delUser")
    public Result<Boolean> delUser(@RequestBody UserDelete req) {
        // 调用 service 层的方法将 user 对象插入到数据库中
        // TODO： 校验
        if(req.uids==null||req.uids.length==0) {
            return Result.fail("params need array of user_id");
        }
        try{
            List<Integer> ids = Arrays.asList(req.uids);
            boolean res =  userService.delUser(ids);
            if (!res) {
                return Result.success("no data delete");
            }
            // 删除用户与角色之间的联系
            int resRows = userRoleService.delByUids(ids);
            res = resRows>0;
            // TODO:是否考虑区别返回值
            return Result.success(res);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "修改用户",notes = "修改用户的描述")
    @PostMapping("/modifyUser")
    public Result modifyUser(@RequestBody User req) {
        // 调用 service 层的方法将 user 对象插入到数据库中
        // TODO： 校验
        if (req == null) {
            return Result.fail("User对象不能为空");
        }
        try {
            // 根据 user 的 id 查询数据库中对应的用户
            User existingUser = userService.getById(req.getUid());
            if (existingUser == null) {
                return Result.fail("要修改的用户不存在");
            }
            try{
                // 更新数据库中的用户信息
                req.setPassword(existingUser.getPassword());
                req.setUpdateAt(LocalDateTime.now());
                boolean success = userService.updateById(req);
                if (success) {
                    return Result.success("用户修改成功");
                } else {
                    return Result.fail("用户修改失败");
                }
            } catch (Exception e) {
                return Result.fail(e);
            }
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "获得所有用户",notes = "获得所有用户的描述")
    @GetMapping("/allUser")
    public Result<List<User>> getAllUser() {
        // 调用 service 层的方法将 user 对象插入到数据库中
        try{
            List<User> users = userService.list();
            return Result.success(users);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "按用户ID获得用户",notes = "按用户ID获得用户的描述")
    @PostMapping("/getUser")
    public Result<User> getUserByUid(@RequestBody UserSearch req) {
        // 调用 service 层的方法将 user 对象插入到数据库中
        try{
            User users = userService.getById(req.uid);
            return Result.success(users);
        }catch (Exception e){
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "按角色ID查询用户",notes = "按角色ID查询用户的描述")
    @PostMapping("/getByRole")
    public Result<List<User>> getUserByRole(@RequestBody UserByRole req) {
        // 调用 service 层的方法将 user 对象插入到数据库中
        try{
            List<User> users = userService.getUserByRole(req.rid);
            return Result.success(users);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "激活用户",notes = "激活用户的描述")
    @PostMapping("/active")
    public Result<Boolean> activeUser(@RequestBody UserActive req) {
        // 调用 service 层的方法将 user 对象插入到数据库中
        try{
            Boolean b = userService.activeUser(req.uid,true);
            return Result.success(b);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "失效用户",notes = "失效用户的描述")
    @PostMapping("/inactive")
    public Result<Boolean> inactiveUser(@RequestBody UserActive req) {
        // 调用 service 层的方法将 user 对象插入到数据库中
        try{
            Boolean b = userService.activeUser(req.uid,false);
            return Result.success(b);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "用户分配角色",notes = "用户分配角色的参数")
    @PostMapping("/assignRoles")
    public Result<Boolean> assginRoles(@RequestBody UserAssign req) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        try{
            // 检查是否存在
            boolean existed = userService.checkUid(req.uid);
            if (!existed) {
                return Result.fail("user not existed");
            }
            List<Integer> include_role = Arrays.asList(req.includeRole);
            // 处理与菜单的联系
            boolean res = userRoleService.assignRole(req.uid,include_role);

            return Result.success(true);
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
