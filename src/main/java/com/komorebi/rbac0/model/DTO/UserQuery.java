package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录的参数")
public class UserQuery extends BasePage {
    @ApiModelProperty("用户名")
    public String username ;
    @ApiModelProperty("角色ID列表")
    public Integer[] rids ;
    @ApiModelProperty("是否激活")
    public Boolean isActive;
}

