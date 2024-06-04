package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("按权限ID查询的参数")
public class PermissionSearch {
    @ApiModelProperty("权限ID")
    public Integer pid;
}
