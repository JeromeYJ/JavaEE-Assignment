package edu.cn.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import edu.cn.demo.domain.ProductDto;
import edu.cn.demo.domain.Product_supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerome
 * @since 2023-11-10
 */

@Mapper
public interface Product_supplierDao extends BaseMapper<Product_supplier> {
    //查询product并返回对应的供应商
    @Select("select * from product ${ew.customSqlSegment}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "supplierList", column = "id",
                    many = @Many(select = "edu.cn.demo.dao.SupplierDao.searchSuppliersByProductId"))})
    public IPage<ProductDto> searchProductsWithSuppliers(IPage<ProductDto> page,
                                                         @Param(Constants.WRAPPER) LambdaQueryWrapper<ProductDto> wrapper);


    //插入到product_supplier表
    @Insert("insert into product_supplier (supplier_id, product_id) values(#{supplier_id},#{product_id})")
    public void insertProductSupplier(int supplier_id, int product_id);


    //删除product_supplier表中对应的product的数据，在删除product时连带进行
    @Delete("delete from product_supplier where product_id = #{product_id}")
    public void deleteProductSuppliers(int product_id);
}
