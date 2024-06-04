package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色分配资源类型的参数")
public class RoleAssign {
    @ApiModelProperty("角色ID")
    public Integer rid;
    @ApiModelProperty("分配的资源类型ID数组")
    public Integer[] includePtype;
}

