package com.komorebi.rbac0.controller;

import com.komorebi.rbac0.common.utils.Result;
import com.komorebi.rbac0.model.DTO.RoleAssign;
import com.komorebi.rbac0.model.DTO.RoleByUser;
import com.komorebi.rbac0.model.DTO.RoleDelete;
import com.komorebi.rbac0.model.DTO.RoleSearch;
import com.komorebi.rbac0.model.Role;
import com.komorebi.rbac0.model.RolePtype;
import com.komorebi.rbac0.service.RolePtypeService;
import com.komorebi.rbac0.service.RoleService;
import com.komorebi.rbac0.service.RoleService;
import com.komorebi.rbac0.service.UserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePtypeService rolePtypeService;
    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "添加角色",notes = "添加角色的描述")
    @PutMapping("/putRole")
    public Result<Boolean> putRole(@RequestBody Role req) {
        // 调用 service 层的方法将 role 对象插入到数据库中
        // TODO： 校验
        if(roleService.checkRoleName(req.getRolename())){
            return Result.fail("role name has been existed");
        }
        req.setRid(null);
        LocalDateTime now = LocalDateTime.now();
        req.setCreateAt(now);
        req.setUpdateAt(now);
        try{
            boolean res = roleService.save(req);
        return Result.success(res);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "删除角色",notes = "删除角色的描述")
    @DeleteMapping("/delRole")
    public Result<Boolean> delRole(@RequestBody RoleDelete req) {
        // 调用 service 层的方法将 role 对象插入到数据库中
        // TODO： 校验
        if(req.rids==null||req.rids.length==0) {
            return Result.fail("params need array of role_id");
        }
        try{
            List<Integer> ids = Arrays.asList(req.rids);
            Integer res =  roleService.delRole(ids);
            if (res==0) {
                return Result.success("no data delete");
            }
            // 删除角色与资源类型之间的联系
            int resRows1 = rolePtypeService.delByRids(ids);
            // TODO:删除角色与用户之间的联系
            int resRows2 = userRoleService.delByRids(ids);
            return Result.success(true);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "修改角色",notes = "修改角色的描述")
    @PostMapping("/modifyRole")
    public Result modifyRole(@RequestBody Role req) {
        // 调用 service 层的方法将 role 对象插入到数据库中
        // TODO： 校验
        if (req == null) {
            return Result.fail("Role对象不能为空");
        }
        try {
            // 根据 role 的 id 查询数据库中对应的菜单
            Role existingRole = roleService.getById(req.getRid());
            if (existingRole == null) {
                return Result.fail("要修改的角色不存在");
            }
        } catch (Exception e) {
            return Result.fail(e);
        }
        try{
            // 更新数据库中的菜单信息
            boolean success = roleService.updateById(req);
            if (success) {
                return Result.success("角色修改成功");
            } else {
                return Result.fail("菜单修改失败");
            }
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "获得所有角色",notes = "获得所有角色的描述")
    @GetMapping("/allRole")
    public Result<List<Role>> getAllRole() {
        // 调用 service 层的方法将 role 对象插入到数据库中
        try{
            List<Role> roles = roleService.list();
            return Result.success(roles);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "通过用户ID获得角色",notes = "通过用户ID获得角色的描述")
    @PostMapping("/getByUser")
    public Result<List<Role>> getRoleByPtype(@RequestBody RoleByUser req) {
        // 调用 service 层的方法将 role 对象插入到数据库中
        try{
            List<Role> roles = roleService.getRoleByUser(req.uid);
            return Result.success(roles);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "按角色ID获得角色",notes = "按角色ID获得角色的描述")
    @PostMapping("/getRole")
    public Result<Role> getRoleByRid(@RequestBody RoleSearch req) {
        // 调用 service 层的方法将 role 对象插入到数据库中
        try{
            Role roles = roleService.getById(req.rid);
            return Result.success(roles);
        }catch (Exception e){
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "角色分配资源类型",notes = "角色分配资源类型的描述")
    @PostMapping("/assignPtype")
    public Result<Boolean> assignRolePermission(@RequestBody RoleAssign req) {
        // 检查角色是否存在
        boolean existed = roleService.checkRole(req.rid);
        if (!existed) {
            return Result.fail("role not existed");
        }
        List<Integer> include_ptype = Arrays.asList(req.includePtype);
        try{
            Boolean res = rolePtypeService.assignPtype(req.rid,include_ptype);
            return Result.success(true);
        }catch (Exception e){
            return Result.fail(e);
        }
    }

}
