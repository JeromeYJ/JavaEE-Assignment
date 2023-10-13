package edu.cn.demo.service;

import edu.cn.demo.entity.Goods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {

    //存放各商品的表，使用Map类型便于通过id查找
    Map<Integer, Goods> goods = new HashMap<>();

    //通过id查找商品
    public Goods searchGoodsById(int id){
        return goods.get(id);
    }

    //通过名称查找商品
    public Goods searchGoodsByName(String name){
        for(Goods g : goods.values()){
            if(g.getName().equals(name))
                return g;
        }
        return null;
    }

    //通过价格查找商品
    public List<Goods> searchGoodsByPrice(double price){
        List<Goods> goodsList = new ArrayList<>();
        for(Goods g : goods.values()){
            if(g.getPrice()==price)
                goodsList.add(g);
        }
        return goodsList;
    }

    //添加商品
    public void addGoods(Goods goods){
        int id = goods.getId();
        //假如现有商品中已经含有该商品，则只改变该商品的数量
        if(this.goods.containsKey(id)){
            int amount = this.goods.get(id).getAmount();
            this.goods.get(id).setAmount(amount + 1);
        }
        else
            this.goods.put(goods.getId(),goods);
    }

    //修改商品信息
    public boolean changeGoods(int id, Goods goods){
        if(!this.goods.containsKey(id))
            return false;
        Goods goods1 = this.goods.get(id);
        goods1.setId(goods.getId());
        goods1.setName(goods.getName());
        goods1.setPrice(goods.getPrice());
        goods1.setAmount(goods.getAmount());
        return true;
    }

    //删除商品信息
    public boolean deleteGoods(int id){
        if(goods.containsKey(id)){
            goods.remove(id);
            return true;
        }
        else return false;
    }
}
