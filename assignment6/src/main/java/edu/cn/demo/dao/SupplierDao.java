package edu.cn.demo.dao;

import edu.cn.demo.domain.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerome
 * @since 2023-10-20
 */

@Mapper
public interface SupplierDao extends BaseMapper<Supplier> {
    @Select("select supplier.* from supplier, product_supplier where product_supplier.product_id = #{product_id} " +
            "and supplier.id = product_supplier.supplier_id")
    public List<Supplier> searchSuppliersByProductId(int product_id);
}
