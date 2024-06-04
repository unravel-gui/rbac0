package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("按资源类型ID搜索的参数")
public class PtypeSearch {
    @ApiModelProperty("资源类型的ID")
    public Integer tid;
}

