package edu.cn.demo.dao;

import edu.cn.demo.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.cn.demo.domain.ProductDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerome
 * @since 2023-10-20
 */

@Mapper
public interface ProductDao extends BaseMapper<Product> {

}
