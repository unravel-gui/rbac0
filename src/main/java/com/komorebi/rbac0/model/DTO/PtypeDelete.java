package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("删除资源类型的参数")
public class PtypeDelete {
    @ApiModelProperty("资源类型的ID数组")
    public Integer[] tids;
}

