package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("按角色查询用户的参数")
public class UserByRole {
    @ApiModelProperty("角色ID")
    public Integer rid;
}
