package com.komorebi.rbac0.controller;

import com.komorebi.rbac0.common.utils.Result;
import com.komorebi.rbac0.model.DTO.MenuByPType;
import com.komorebi.rbac0.model.DTO.MenuDelete;
import com.komorebi.rbac0.model.DTO.MenuSearch;
import com.komorebi.rbac0.model.Menu;
import com.komorebi.rbac0.service.MenuService;
import com.komorebi.rbac0.service.PtypeMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/menu")
@Api(tags = "菜单模块", description = "菜单模块描述")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private PtypeMenuService ptypeMenuService;

    @ApiOperation(value = "添加菜单",notes = "添加菜单的描述")
    @PutMapping("/putMenu")
    public Result<Boolean> putMenu(@RequestBody Menu menu) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        // TODO： 校验
        menu.setMid(null);
        try{
            boolean res = menuService.save(menu);
        return Result.success(res);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "删除菜单",notes = "删除菜单的描述")
    @DeleteMapping("/delMenu")
    public Result<Boolean> delMenu(@RequestBody MenuDelete md) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        // TODO： 校验
        if(md.mids.length==0) {
            return Result.fail("params need array of menu_id");
        }
        try{
            List<Integer> ids = Arrays.asList(md.mids);
            boolean res =  menuService.delMenu(ids);
            if (!res) {
                return Result.success("no data delete");
            }
            // 删除菜单与资源类型之间的联系
            int resRows = ptypeMenuService.delByMids(ids);
            res = resRows>0;
            // TODO:是否考虑区别返回值
            return Result.success(res);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "修改菜单",notes = "修改菜单的描述")
    @PostMapping("/modifyMenu")
    public Result modifyMenu(@RequestBody Menu menu) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        // TODO： 校验
        if (menu == null) {
            return Result.fail("Menu对象不能为空");
        }
        try {
            // 根据 menu 的 id 查询数据库中对应的菜单
            Menu existingMenu = menuService.getById(menu.getMid());
            if (existingMenu == null) {
                return Result.fail("要修改的菜单不存在");
            }
        } catch (Exception e) {
            return Result.fail(e);
        }
        try{
            // 更新数据库中的菜单信息
            boolean success = menuService.updateById(menu);
            if (success) {
                return Result.success("菜单修改成功");
            } else {
                return Result.fail("菜单修改失败");
            }
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "获得所有菜单",notes = "获得所有菜单的描述")
    @GetMapping("/allMenu")
    public Result<List<Menu>> getAllMenu() {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        try{
            List<Menu> menus = menuService.getAllMenu();
            return Result.success(menus);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "通过资源类型获得菜单",notes = "通过资源类型获得菜单的描述")
    @PostMapping("/getByPtype")
    public Result<List<Menu>> getMenuByPtype(@RequestBody MenuByPType mbp) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        try{
            List<Menu> menus = menuService.getMenuByPtype(mbp.tid);
            return Result.success(menus);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @ApiOperation(value = "按菜单ID查询菜单",notes = "按菜单ID查询菜单的描述")
    @PostMapping("/getMenu")
    public Result<Menu> getMenuByMid(@RequestBody MenuSearch ms) {
        // 调用 service 层的方法将 menu 对象插入到数据库中
        try{
            Menu menus = menuService.getById(ms.mid);
            return Result.success(menus);
        }catch (Exception e){
            return Result.fail(e);
        }
    }
}
