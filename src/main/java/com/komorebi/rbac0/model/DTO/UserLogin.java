package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("登录的参数")
public class UserLogin {
    @ApiModelProperty("用户名")
    public String username ;
    @ApiModelProperty("密码")
    public String password ;
}

