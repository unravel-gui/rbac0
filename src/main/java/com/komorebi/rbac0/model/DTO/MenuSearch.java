package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("按菜单ID查询的参数")
public class MenuSearch {
    @ApiModelProperty(value = "菜单ID")
    public Integer mid;
}
