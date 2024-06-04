package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("通过角色查询资源类型的参数")
public class PTypeByRole {
    @ApiModelProperty("角色ID")
    public Integer rid;
}
