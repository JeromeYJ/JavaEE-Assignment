package edu.cn.demo.service.impl;

import edu.cn.demo.domain.User;
import edu.cn.demo.dao.UserDao;
import edu.cn.demo.domain.UserDto;
import edu.cn.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerome
 * @since 2023-11-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    @Cacheable(cacheNames = "user",key = "#id",condition = "#id!=null")
    public UserDto getUserById(String id){
        return this.baseMapper.searchUser(id);
    }


    public void addUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        this.baseMapper.insert(user);
    }


    // 移除缓存user中key为id的对象
    @CacheEvict(cacheNames = "user",key = "#id")
    public void deleteUser(String id) {
        this.baseMapper.deleteById(id);
    }


    // 移除缓存user中key为id的对象
    @CacheEvict(cacheNames = "user",key = "#user.id")
    public void updateUser(User user) {
        this.baseMapper.updateById(user);
    }

}
