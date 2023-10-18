package edu.cn.demo;

import edu.cn.demo.entity.Goods;
import edu.cn.demo.service.GoodsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductApiTest {
    @Value(value="${server.port}")
    private int port;

    @Autowired
    GoodsService goodsService;

    //@Autowired
    TestRestTemplate testRestTemplate;

    //在每个@Test函数执行前进行
    @BeforeEach
    private void initData(){
        testRestTemplate = new TestRestTemplate();
        Goods goods = new Goods();
        goods.setId(1);
        goods.setName("《exile》");
        goods.setPrice(800);
        goods.setAmount(3);
        goodsService.addGoods(goods);
    }

    @Test
    public void testPost(){
        Goods goods = new Goods();
        goods.setId(2);
        goods.setName("《-》");
        goods.setPrice(500);
        goods.setAmount(2);
        goodsService.addGoods(goods);

        ResponseEntity<Goods> result = testRestTemplate.postForEntity("http://localhost:" + port + "/goods", goods, Goods.class);
        assertEquals(HttpStatus.OK,result.getStatusCode());

        Goods tmp = goodsService.searchGoodsById(2);
        assertEquals("《-》",tmp.getName());
    }

    @Test
    public void testPut(){
        Goods goods = new Goods();
        goods.setId(3);
        goods.setName("《+》");
        goods.setPrice(100);
        goods.setAmount(5);
        goodsService.addGoods(goods);

        testRestTemplate.put("http://localhost:" + port + "/goods/3", goods, Goods.class);
        goods = goodsService.searchGoodsById(3);
    }

}
