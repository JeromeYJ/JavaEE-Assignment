package edu.cn.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.cn.demo.domain.ProductDto;
import edu.cn.demo.domain.Product_supplier;
import edu.cn.demo.service.impl.Product_supplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/product_supplier")
public class Product_supplierController {
    @Autowired
    Product_supplierServiceImpl productSupplierService;

    //查找product并返回对应的supplier信息
    @GetMapping("")
    public ResponseEntity<IPage<ProductDto>> getProductWithSuppliers(Integer id, String name, Double price, Integer amount, String supplierName,
                                                                     @RequestParam(defaultValue = "0")Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize){

        //创建条件对应的map
        Map<String,Object> condition = new HashMap<>();

        if(id != null) {
            condition.put("id", id);
        }
        if(name != null) {
            condition.put("name",name);
        }
        if(price != null) {
            condition.put("price",price);
        }
        if(amount != null) {
            condition.put("amount",amount);
        }
        IPage<ProductDto> result = productSupplierService.searchByProductIdWithSuppliers(condition, pageNum, pageSize);
        return ResponseEntity.ok(result);
    }


    @PostMapping("")
    public ResponseEntity<Product_supplier> add(@RequestBody Product_supplier product_supplier){
        productSupplierService.add(product_supplier);
        return ResponseEntity.ok(product_supplier);
    }
}

