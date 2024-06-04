package com.komorebi.rbac0.model.DTO;

import com.komorebi.rbac0.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

@ApiModel("登录或注册的返回值")
@AllArgsConstructor
public class UserLoginOrRegisterResp {
    @ApiModelProperty("用户")
    public User user;
    @ApiModelProperty("JWT令牌")
    public String jwt_token;
}
