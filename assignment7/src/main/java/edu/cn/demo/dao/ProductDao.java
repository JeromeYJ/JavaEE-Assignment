package edu.cn.demo.dao;

import edu.cn.demo.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerome
 * @since 2023-11-10
 */

@Mapper
public interface ProductDao extends BaseMapper<Product> {

}
