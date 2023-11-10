package edu.cn.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.cn.demo.domain.Supplier;
import edu.cn.demo.dao.SupplierDao;
import edu.cn.demo.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, Supplier> implements ISupplierService {
    //查询的相关方法
    public Supplier searchById(Integer id){
        return this.getById(id);
    }

    public Supplier searchByName(String name){
        LambdaQueryWrapper<Supplier> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Supplier::getName, name);
        return this.getOne(lqw);
    }

    public Supplier searchByAddress(String address){
        LambdaQueryWrapper<Supplier> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Supplier::getAddress, address);
        return this.getOne(lqw);
    }

    //添加数据的相关方法
    public boolean add(Supplier supplier){
        return this.save(supplier);
    }

    //修改数据的相关方法
    public boolean change(Supplier supplier){
        return this.updateById(supplier);
    }

    //删除数据相关的方法
    public boolean delete(Integer id){
        return this.removeById(id);
    }
}
