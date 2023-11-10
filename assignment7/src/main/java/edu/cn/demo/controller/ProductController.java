package edu.cn.demo.controller;


import edu.cn.demo.domain.Product;
import edu.cn.demo.exception.ProductException;
import edu.cn.demo.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;


    //添加商品
    @PostMapping("")
    @PreAuthorize("hasAuthority('product/admin')")
    public Product add(@RequestBody Product product) throws ProductException {
        return productService.add(product);
    }

    //修改供应商信息
    @PutMapping("")
    @PreAuthorize("hasAuthority('product/admin')")
    public Product change(@RequestBody Product product) throws ProductException {
        return productService.change(product);
    }

    //删除商品
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('product/admin')")
    public void delete(@PathVariable int id) throws ProductException {
        productService.delete(id);
    }


    //通过id查找
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('product/query')")
    public Product getById(@PathVariable int id) throws ProductException {
        Product result = productService.searchById(id);
        return result;
    }

    //通过商品名称查找
    @GetMapping("/name")
    @PreAuthorize("hasAuthority('product/query')")
    public Product getByName(String name) throws ProductException {
        Product result = productService.searchByName(name);
        return result;
    }

    //通过商品价格查找
    @GetMapping("/price")
    @PreAuthorize("hasAuthority('product/query')")
    public List<Product> getByPrice(Double price) throws ProductException {
        List<Product> result = productService.searchByPrice(price);
        return result;
    }

    //通过商品数量查找
    @GetMapping("/amount")
    @PreAuthorize("hasAuthority('product/query')")
    public List<Product> getByAmount(int amount) throws ProductException {
        List<Product> result = productService.searchByAmount(amount);
        return result;
    }

    //通过商品价格范围查找
    @GetMapping("/priceRange")
    @PreAuthorize("hasAuthority('product/query')")
    public List<Product> getByPriceRange(Double lowPrice, Double highPrice) throws ProductException {
        List<Product> result = productService.searchByPriceRange(lowPrice, highPrice);
        return result;
    }

    //通过商品价格范围查找
    @GetMapping("/amountRange")
    @PreAuthorize("hasAuthority('product/query')")
    public List<Product> getByAmountRange(int lowAmount, int highAmount) throws ProductException {
        List<Product> result = productService.searchByAmountRange(lowAmount, highAmount);
        return result;
    }


}

