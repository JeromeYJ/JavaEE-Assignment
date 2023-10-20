package edu.cn.demo.domain;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto extends Product{

    List<Supplier> supplierList;
}
