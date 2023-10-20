package edu.cn.demo.controller;


import edu.cn.demo.domain.Product;
import edu.cn.demo.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerome
 * @since 2023-10-20
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    //通过id查找
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id){
        Product result = productService.searchById(id);
        if(result==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //通过商品名称查找
    @GetMapping("/name")
    public ResponseEntity<Product> getByName(String name){
        Product result = productService.searchByName(name);
        if(result==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //通过商品价格查找
    @GetMapping("/price")
    public ResponseEntity<List<Product>> getByPrice(Double price){
        List<Product> result = productService.searchByPrice(price);
        if(result.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //通过商品数量查找
    @GetMapping("/amount")
    public ResponseEntity<List<Product>> getByAmount(int amount){
        List<Product> result = productService.searchByAmount(amount);
        if(result.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //通过商品价格范围查找
    @GetMapping("/priceRange")
    public ResponseEntity<List<Product>> getByPriceRange(Double lowPrice, Double highPrice){
        List<Product> result = productService.searchByPriceRange(lowPrice, highPrice);
        if(result.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //通过商品价格范围查找
    @GetMapping("/amountRange")
    public ResponseEntity<List<Product>> getByAmountRange(int lowAmount, int highAmount){
        List<Product> result = productService.searchByAmountRange(lowAmount, highAmount);
        if(result.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //添加商品
    @PostMapping("")
    public ResponseEntity<Product> add(@RequestBody Product product){
        productService.add(product);
        return ResponseEntity.ok(product);
    }

    //修改供应商信息
    @PutMapping("")
    public ResponseEntity<Void> change(@RequestBody Product product){
        if(productService.change(product))
            return ResponseEntity.ok().build();
        else
            //修改失败，不存在对应id的供应商
            return ResponseEntity.noContent().build();
    }

    //删除商品
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(productService.delete(id))
            return ResponseEntity.ok().build();
        else
            //删除失败，不存在对应id的供应商
            return ResponseEntity.noContent().build();
    }
}

