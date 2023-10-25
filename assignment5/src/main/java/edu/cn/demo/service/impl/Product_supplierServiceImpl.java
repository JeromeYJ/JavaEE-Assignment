package edu.cn.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.cn.demo.domain.Product;
import edu.cn.demo.domain.ProductDto;
import edu.cn.demo.domain.Product_supplier;
import edu.cn.demo.dao.Product_supplierDao;
import edu.cn.demo.domain.Supplier;
import edu.cn.demo.service.IProduct_supplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerome
 * @since 2023-10-20
 */
@Service
public class Product_supplierServiceImpl extends ServiceImpl<Product_supplierDao, Product_supplier> implements IProduct_supplierService {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    SupplierServiceImpl supplierService;

    public List<Product_supplier> searchByProductId(Integer id){
        LambdaQueryWrapper<Product_supplier> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product_supplier::getProduct_id, id);
        return this.getBaseMapper().selectList(lqw);
    }

    //查询product并且返回对应suppliers
    public IPage<ProductDto> searchByProductIdWithSuppliers(Map<String, Object> condition, int pageNum, int pageSize){
        Page<ProductDto> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<ProductDto> lqw = new LambdaQueryWrapper<>();
        lqw.eq(condition.containsKey("id"), Product::getId, condition.get("id"));
        lqw.eq(condition.containsKey("name"), Product::getName, condition.get("name"));
        lqw.eq(condition.containsKey("price"), Product::getPrice, condition.get("price"));
        lqw.eq(condition.containsKey("amount"), Product::getAmount, condition.get("amount"));

        this.getBaseMapper().searchProductsWithSuppliers(page, lqw);
        return page;
    }

    public boolean add(Product_supplier product_supplier){
        return this.save(product_supplier);
    }

    /*
    //通过id查找product与其供货商，都存于ProductDto类型中
    public ProductDto searchByIdWithSuppliers(Integer id){
        ProductDto productDto = new ProductDto();
        Product product = productService.getById(id);
        productDto.setId(((Product) product).getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setAmount(product.getAmount());

        //productSupplierList = new ArrayList<>();
        List<Product_supplier> productSupplierList = this.searchByProductId(id);
        List<Supplier> supplierList = new ArrayList<>();

        for(Product_supplier ps : productSupplierList){
            supplierList.add(supplierService.searchById(ps.getSupplier_id()));
        }
        productDto.setSupplierList(supplierList);

        return productDto;
    }
    */

}
