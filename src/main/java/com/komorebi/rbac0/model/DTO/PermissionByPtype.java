package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("通过资源类型寻找权限")
public class PermissionByPtype {
    @ApiModelProperty("资源类型ID")
    public Integer tid;
}
