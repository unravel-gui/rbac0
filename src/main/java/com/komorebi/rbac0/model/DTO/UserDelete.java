package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("删除用户的参数")
public class UserDelete {
    @ApiModelProperty("角色的ID数组")
    public Integer[] uids;
}

