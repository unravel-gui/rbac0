package com.komorebi.rbac0.controller;

import com.komorebi.rbac0.common.utils.Result;
import com.komorebi.rbac0.model.DTO.*;
import com.komorebi.rbac0.model.Ptype;
import com.komorebi.rbac0.service.PtypeMenuService;
import com.komorebi.rbac0.service.PtypePermissionService;
import com.komorebi.rbac0.service.PtypeService;
import com.komorebi.rbac0.service.RolePtypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/ptype")
public class PtypeController {
    @Autowired
    private PtypeService ptypeService;
    @Autowired
    private PtypeMenuService ptypeMenuService;
    @Autowired
    private PtypePermissionService ptypePermissionService;
    @Autowired
    private RolePtypeService rolePtypeService;

    @ApiOperation(value = "添加资源类型",notes = "添加资源类型的描述")
    @PutMapping("/putPtype")
    public Result<Boolean> putPtype(@RequestBody Ptype req) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        if (req.getName()==null||req.getName()==""){
            return Result.fail("name should not be empty");
        }
        if(ptypeService.checkPtypename(req.getName())) {
            return Result.fail("name already existed");
        }
        req.setTid(null);
        try{
            boolean res = ptypeService.save(req);
            return Result.success(res);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "删除资源类型",notes = "删除资源类型的描述")
    @DeleteMapping("/delPtype")
    public Result<Boolean> delPtype(@RequestBody PtypeDelete req) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        // TODO： 校验
        if(req.tids.length==0) {
            return Result.fail("params need array of ptype_id");
        }
        try{
            List<Integer> tids = Arrays.asList(req.tids);
            boolean res =  ptypeService.delPtype(tids);
            if (!res) {
                return Result.success("no data delete");
            }
            // 删除菜单与资源类型之间的联系
            int resRows1 = ptypeMenuService.delByTids(tids);
            // 删除权限与资源类型之间的联系
            int resRows2 = ptypePermissionService.delByTids(tids);
            // 删除用户与资源类型之间的联系
            int resRows3 = rolePtypeService.delByTids(tids);
            return Result.success(true);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "修改资源类型",notes = "修改资源类型的描述")
    @PostMapping("/modifyPtype")
    public Result modifyPtype(@RequestBody Ptype req) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        // TODO： 校验
        if (req == null) {
            return Result.fail("资源类型对象不能为空");
        }
        if (req.getName()==null||req.getName()==""){
            return Result.fail("name should not be empty");
        }
        if(ptypeService.checkPtypename(req.getName())) {
            return Result.fail("name already existed");
        }
        try{
            // 根据 menu 的 id 查询数据库中对应的菜单
            Ptype existing = ptypeService.getById(req.getTid());
            if (existing == null) {
                return Result.fail("要修改的资源类型不存在");
            }
        } catch (Exception e) {
            return Result.fail(e);
        }

        try {
            // 更新数据库中的菜单信息
            boolean success = ptypeService.updateById(req);
            if (success) {
                return Result.success("资源类型修改成功");
            } else {
                return Result.fail("资源类型修改失败");
            }
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "查询所有的资源种类",notes = "查询所有的资源种类的描述")
    @GetMapping("/allPtype")
    public Result<List<Ptype>> getAllPtype() {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        try{
            List<Ptype> ptypes = ptypeService.list();
            return Result.success(ptypes);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "通过角色查询菜单",notes = "通过角色查询菜单的描述")
    @PostMapping("/getByRole")
    public Result<List<Ptype>> getMenuByPtype(@RequestBody PTypeByRole req) {
        try{
            // 调用 service 层的方法将 menu 对象插入到数据库中
            List<Ptype> ptypes = ptypeService.getPtypeByRole(req.rid);
            return Result.success(ptypes);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "通过资源类型查询菜单",notes = "通过资源类型查询菜单的描述")
    @PostMapping("/getPtype")
    public Result<Ptype> getMenuByTid(@RequestBody PtypeSearch req) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        try{
            Ptype ptype = ptypeService.getById(req.tid);
            return Result.success(ptype);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }


    @ApiOperation(value = "资源类型分配资源",notes = "资源类型分配资源，资源包含菜单和权限")
    @PostMapping("/assignResource")
    public Result<Boolean> assignResource(@RequestBody PtypeAssign req) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        try{
            // 检查是否存在
            boolean existed = ptypeService.checkPtype(req.tid);
            if (!existed) {
                return Result.fail("ptype not existed");
            }
            List<Integer> include_menu = Arrays.asList(req.includeMenu);
            List<Integer> include_permisson = Arrays.asList(req.includePermission);
            // 处理与菜单的联系
            boolean res1 = ptypeMenuService.assignMenu(req.tid,include_menu);
            // 处理与权限的联系
            boolean res2 = ptypePermissionService.assignPermission(req.tid,include_permisson);

            return Result.success(true);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }
}
