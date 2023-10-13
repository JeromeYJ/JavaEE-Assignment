package edu.cn.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description="商品实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    @ApiModelProperty("商品编号")
    int id;

    @ApiModelProperty("商品名称")
    String name;

    @ApiModelProperty("商品价格")
    double price;

    @ApiModelProperty("商品数量")
    int amount;
}
