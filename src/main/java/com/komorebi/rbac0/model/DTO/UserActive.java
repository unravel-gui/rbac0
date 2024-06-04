package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("激活用户的参数")
public class UserActive {
    @ApiModelProperty("用户ID")
    public Integer uid;
}
