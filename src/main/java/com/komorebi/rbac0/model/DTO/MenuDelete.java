package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "删除用户参数")
public class MenuDelete {

    @ApiModelProperty(value = "菜单的ID数组")
    public Integer[] mids;
}

