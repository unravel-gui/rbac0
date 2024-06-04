package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("用户分配角色的参数")
public class UserAssign {
    @ApiModelProperty("角色ID")
    public Integer uid;
    @ApiModelProperty("分配的角色ID数组")
    public Integer[] includeRole;
}

