package edu.cn.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.cn.demo.domain.Product;
import edu.cn.demo.dao.ProductDao;
import edu.cn.demo.domain.ProductDto;
import edu.cn.demo.domain.Product_supplier;
import edu.cn.demo.domain.Supplier;
import edu.cn.demo.exception.ProductException;
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
    public Product searchById(Integer id) throws ProductException {
        Product result = this.getById(id);
        if(result == null){
            throw new ProductException(ProductException.GET_ERROR, "不存在此id的商品！");
        }
        return result;
    }

    public Product searchByName(String name) throws ProductException{
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getName, name);
        Product result = this.getOne(lqw);
        if(result == null){
            throw new ProductException(ProductException.GET_ERROR, "不存在此名称的商品！");
        }
        return result;
    }

    //通过准确价格price查询product
    public List<Product> searchByPrice(Double price) throws ProductException{
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getPrice, price);
        List<Product> result = this.list(lqw);      //查看源代码可知，其中调用了成员baseMapper的selectList方法
        if(result.isEmpty()){
            throw new ProductException(ProductException.GET_ERROR, "不存在此价格的商品！");
        }
        return result;
    }

    //通过价格price上下限范围查询product
    public List<Product> searchByPriceRange(Double lowPrice, Double highPrice) throws ProductException{
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.lambda().gt(Product::getPrice, lowPrice).lt(Product::getPrice, highPrice);
        List<Product> result = this.list(qw);
        if(result.isEmpty()){
            throw new ProductException(ProductException.GET_ERROR, "不存在此价格范围的商品！");
        }
        return result;
    }

    public List<Product> searchByAmount(Integer amount) throws ProductException{
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getAmount, amount);
        List<Product> result =  this.list(lqw);
        if(result.isEmpty()){
            throw new ProductException(ProductException.GET_ERROR, "不存在此数量的商品！");
        }
        return result;
    }

    public List<Product> searchByAmountRange(Integer lowAmount, Integer highAmount) throws ProductException{
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.lambda().gt(Product::getAmount, lowAmount).lt(Product::getAmount, highAmount);
        List<Product> result = this.list(qw);
        if(result.isEmpty()){
            throw new ProductException(ProductException.GET_ERROR, "不存在此数量范围的商品！");
        }
        return result;
    }

    //添加数据相关的方法
    public Product add(Product product) throws ProductException{
        if(!this.save(product))
            throw new ProductException(ProductException.INPUT_ERROR, "添加失败！");

        return product;
    }

    //修改数据的相关方法
    public Product change(Product product) throws ProductException{
        if(!this.updateById(product))
            throw new ProductException(ProductException.UPDATE_ERROR, "修改失败！");
        return product;
    }

    //删除数据的相关方法
    public void delete(Integer id) throws ProductException{
        if(!this.removeById(id))
            throw new ProductException(ProductException.INPUT_ERROR, "删除失败！");
    }
}
