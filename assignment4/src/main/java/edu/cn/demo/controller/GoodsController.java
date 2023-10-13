package edu.cn.demo.controller;

import edu.cn.demo.entity.Goods;
import edu.cn.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    //通过id查找
    @GetMapping("/{id}")
    public ResponseEntity<Goods> getGoodsById(@PathVariable int id){
        Goods result = goodsService.searchGoodsById(id);
        if(result==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //通过商品名称查找
    @GetMapping("")
    public ResponseEntity<Goods> getGoodsByName(String name){
        Goods result = goodsService.searchGoodsByName(name);
        if(result==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //添加商品
    @PostMapping("")
    public ResponseEntity<Goods> addGoods(@RequestBody Goods goods){
        goodsService.addGoods(goods);
        return ResponseEntity.ok(goods);
    }

    //修改商品信息
    @PutMapping("/{id}")
    public ResponseEntity<Void> changeGoods(@PathVariable int id, @RequestBody Goods goods){
        if(goodsService.changeGoods(id, goods))
            return ResponseEntity.ok().build();
        else
            //修改失败，不存在对应id的商品
            return ResponseEntity.noContent().build();
    }

    //删除商品
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoods(@PathVariable int id){
        if(goodsService.deleteGoods(id))
            return ResponseEntity.ok().build();
        else
            //删除失败，不存在对应id的商品
            return ResponseEntity.noContent().build();
    }
}
