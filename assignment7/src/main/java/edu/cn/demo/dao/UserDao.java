package edu.cn.demo.dao;

import edu.cn.demo.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.cn.demo.domain.UserDto;
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
public interface UserDao extends BaseMapper<User> {

    @Select("select * from user where id =  #{id}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id",
                    many = @Many(select = "edu.cn.demo.dao.RoleDao.searchRolesByUser"))})
    public UserDto searchUser(String id);
}
