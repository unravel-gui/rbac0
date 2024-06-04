package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("资源类型分配资源的参数")
public class PtypeAssign {
    @ApiModelProperty("资源类型ID")
    public Integer tid;
    @ApiModelProperty("分配的菜单ID数组")
    public Integer[] includeMenu;
    @ApiModelProperty("分配的权限ID数组")
    public Integer[] includePermission;
}

