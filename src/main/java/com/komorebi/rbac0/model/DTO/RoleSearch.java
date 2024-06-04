package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("按角色ID查询的参数")
public class RoleSearch {
    @ApiModelProperty("角色ID")
    public Integer rid;
}

