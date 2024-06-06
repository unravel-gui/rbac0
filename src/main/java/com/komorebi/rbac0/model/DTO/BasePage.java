package com.komorebi.rbac0.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("分页参数基类")
public class BasePage {
    @ApiModelProperty("页码")
    public Integer page_num=0;
    @ApiModelProperty("每页大小")
    public Integer page_size=20;

    public Integer getPage_num() {
        return page_num;
    }

    public void setPage_num(Integer page_num) {
        if (page_num==null){
            page_num = 0;
        }
        this.page_num = page_num;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        if (page_size==null){
            page_size = 20;
        }
        this.page_size = page_size;
    }
}
