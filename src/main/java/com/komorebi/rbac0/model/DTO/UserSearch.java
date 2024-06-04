package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("按用户ID查询的参数")
public class UserSearch {
    @ApiModelProperty("角色ID")
    public Integer uid;
}

