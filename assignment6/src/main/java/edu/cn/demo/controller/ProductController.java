package edu.cn.demo.controller;


import edu.cn.demo.domain.Product;
import edu.cn.demo.exception.ProductException;
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
    public Product getById(@PathVariable int id) throws ProductException {
        Product result = productService.searchById(id);
        return result;
    }

    //通过商品名称查找
    @GetMapping("/name")
    public Product getByName(String name) throws ProductException {
        Product result = productService.searchByName(name);
        return result;
    }

    //通过商品价格查找
    @GetMapping("/price")
    public List<Product> getByPrice(Double price) throws ProductException {
        List<Product> result = productService.searchByPrice(price);
        return result;
    }

    //通过商品数量查找
    @GetMapping("/amount")
    public List<Product> getByAmount(int amount) throws ProductException {
        List<Product> result = productService.searchByAmount(amount);
        return result;
    }

    //通过商品价格范围查找
    @GetMapping("/priceRange")
    public List<Product> getByPriceRange(Double lowPrice, Double highPrice) throws ProductException {
        List<Product> result = productService.searchByPriceRange(lowPrice, highPrice);
        return result;
    }

    //通过商品价格范围查找
    @GetMapping("/amountRange")
    public List<Product> getByAmountRange(int lowAmount, int highAmount) throws ProductException {
        List<Product> result = productService.searchByAmountRange(lowAmount, highAmount);
        return result;
    }

    //添加商品
    @PostMapping("")
    public Product add(@RequestBody Product product) throws ProductException {
        return productService.add(product);
    }

    //修改供应商信息
    @PutMapping("")
    public Product change(@RequestBody Product product) throws ProductException {
        return productService.change(product);
    }

    //删除商品
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws ProductException {
        productService.delete(id);
    }
}

