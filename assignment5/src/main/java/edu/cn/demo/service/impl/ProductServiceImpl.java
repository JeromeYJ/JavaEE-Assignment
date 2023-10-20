package edu.cn.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.cn.demo.domain.Product;
import edu.cn.demo.dao.ProductDao;
import edu.cn.demo.domain.ProductDto;
import edu.cn.demo.domain.Product_supplier;
import edu.cn.demo.domain.Supplier;
import edu.cn.demo.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerome
 * @since 2023-10-20
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements IProductService {

    //查询的相关方法
    public Product searchById(Integer id){
        return this.getById(id);
    }

    public Product searchByName(String name){
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getName, name);
        return this.getOne(lqw);
    }

    //通过准确价格price查询product
    public List<Product> searchByPrice(Double price){
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getPrice, price);
        return this.list(lqw);      //查看源代码可知，其中调用了成员baseMapper的selectList方法
    }

    //通过价格price上下限范围查询product
    public List<Product> searchByPriceRange(Double lowPrice, Double highPrice){
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.lambda().gt(Product::getPrice, lowPrice).lt(Product::getPrice, highPrice);
        return this.list(qw);
    }

    public List<Product> searchByAmount(Integer amount){
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getAmount, amount);
        return this.list(lqw);
    }

    public List<Product> searchByAmountRange(Integer lowAmount, Integer highAmount){
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.lambda().gt(Product::getAmount, lowAmount).lt(Product::getAmount, highAmount);
        return this.list(qw);
    }

    //添加数据相关的方法
    public boolean add(Product product){
        return this.save(product);
    }

    //修改数据的相关方法
    public boolean change(Product product){
        return this.updateById(product);
    }

    //删除数据的相关方法
    public boolean delete(Integer id){
        return this.removeById(id);
    }
}
