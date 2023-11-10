package edu.cn.demo.dao;

import edu.cn.demo.domain.Role;
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
 * @since 2023-11-10
 */

@Mapper
public interface RoleDao extends BaseMapper<Role> {
    @Select("select role.* from role, user_role " +
            "where role.id = user_role.role_id " +
            "and user_role.user_id = #{userId}")
    public List<Role> searchRolesByUser(String userId);
}
