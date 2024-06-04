package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("删除角色的参数")
public class RoleDelete {
    @ApiModelProperty("角色ID")
    public Integer[] rids;
}

