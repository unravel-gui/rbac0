package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("按用户查询角色的参数")
public class RoleByUser {
    @ApiModelProperty("用户ID")
    public Integer uid;
}
