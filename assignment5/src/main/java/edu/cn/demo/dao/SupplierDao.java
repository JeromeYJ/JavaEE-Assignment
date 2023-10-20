package edu.cn.demo.dao;

import edu.cn.demo.domain.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
