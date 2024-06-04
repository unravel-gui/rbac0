package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel(description = "通过资源类型寻找菜单的参数")
public class MenuByPType {
    @ApiModelProperty(value = "资源类型ID")
    public Integer tid;
}
