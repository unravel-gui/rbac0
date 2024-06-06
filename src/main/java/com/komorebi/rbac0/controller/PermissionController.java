package com.komorebi.rbac0.controller;

import com.komorebi.rbac0.common.Common;
import com.komorebi.rbac0.common.utils.Result;
import com.komorebi.rbac0.model.DTO.PermissionByPtype;
import com.komorebi.rbac0.model.DTO.PermissionSearch;
import com.komorebi.rbac0.model.Permission;
import com.komorebi.rbac0.service.PermissionService;
import com.komorebi.rbac0.service.PtypePermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PtypePermissionService ptypePermissionService;

    @ApiOperation(value = "添加权限",notes = "添加权限的描述")
    @PutMapping("/putPermission")
    public Result<Boolean> putPermission(@RequestBody Permission req) {
        // 调用 service 层的方法将 permission 对象插入到数据库中
        // TODO： 校验
        if(permissionService.checkPermissionName(req.getName())){
            return Result.fail("permission name has been existed");
        }
        req.setPid(null);
        LocalDateTime now = LocalDateTime.now();
        req.setCreateAt(now);
        req.setUpdateAt(now);
        try{
            boolean res = permissionService.save(req);
        return Result.success(res);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }


    @ApiOperation(value = "修改权限",notes = "修改权限的描述")
    @PostMapping("/modifyPermission")
    public Result modifyPermission(@RequestBody Permission req) {
        // 调用 service 层的方法将 permission 对象插入到数据库中
        // TODO： 校验
        if (req == null) {
            return Result.fail("Permission对象不能为空");
        }
        try {
            // 根据 permission 的 id 查询数据库中对应的权限
            Permission existingPermission = permissionService.getById(req.getPid());
            if (existingPermission == null) {
                return Result.fail("要修改的权限不存在");
            }
        } catch (Exception e) {
            return Result.fail(e);
        }
        try{
            // 更新数据库中的权限信息
            boolean success = permissionService.updateById(req);
            if (success) {
                return Result.success("权限修改成功");
            } else {
                return Result.fail("权限修改失败");
            }
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "获得所有权限",notes = "获得所有权限的描述")
    @GetMapping("/allPermission")
    public Result<List<Permission>> getAllPermission() {
        // 调用 service 层的方法将 permission 对象插入到数据库中
        try{
            List<Permission> permissions = permissionService.list();
            return Result.success(permissions);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "通过资源类型查询权限",notes = "通过资源类型查询权限的描述")
    @PostMapping("/getByPtype")
    public Result<List<Permission>> getPermissionByPtype(@RequestBody PermissionByPtype req) {
        // 调用 service 层的方法将 permission 对象插入到数据库中
        try{
            List<Permission> permissions = permissionService.getPermissionByPtype(req.tid);
            return Result.success(permissions);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "按权限ID查询权限",notes = "按权限ID查询权限的描述")
    @PostMapping("/getPermission")
    public Result<Permission> getPermissionByPid(@RequestBody PermissionSearch req) {
        // 调用 service 层的方法将 permission 对象插入到数据库中
        try{
            Permission permissions = permissionService.getById(req.pid);
            return Result.success(permissions);
        }catch (Exception e){
            return Result.fail(e);
        }
    }

}
