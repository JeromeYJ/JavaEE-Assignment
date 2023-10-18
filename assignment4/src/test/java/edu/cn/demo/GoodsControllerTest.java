package edu.cn.demo;

import edu.cn.demo.entity.Goods;
import edu.cn.demo.service.GoodsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GoodsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean   //”虚假"的Bean。用MockBean来替代真实的GoodsService，目的是指关注与Controller相关的部分
    @Autowired
    private GoodsService goodsService;


    @BeforeEach
    private void init(){
        Goods goods=new Goods();
        goods.setId(1);
        goods.setName("《郭源潮》");
        goods.setPrice(300);
        goods.setAmount(3);
        goodsService.addGoods(goods);
    }

    //对get方法的测试
    @Test
    public void getTest() throws Exception {
        ResultActions resultAction = this.mockMvc.perform(get("/goods/1"))
                                .andDo(print())
                                .andExpect(status().isOk());

        resultAction.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    //对post方法的测试（这里还有疑问）
    @Test
    public void postTest() throws Exception {
        String requestBody = "{\"id\": 2,\"name\":\"《安河桥北》\",\"price\":150,\"amount\": 2}";
        ResultActions resultAction1 = this.mockMvc.perform(post("/goods")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andDo(print())
                                .andExpect(status().isOk());

        requestBody = "{\"id\": 3,\"name\":\"《-》\",\"price\":200,\"amount\": 1}";
        ResultActions resultAction2 = this.mockMvc.perform(post("/goods")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andDo(print())
                                .andExpect(status().isOk());

        resultAction1.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
        resultAction2.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("《-》"));
        assertNotNull(goodsService.searchGoodsById(2));
        assertNotNull(goodsService.searchGoodsById(3));
    }

    //对put方法的测试
    @Test
    public void putTest() throws Exception {
        Goods goods=new Goods();
        goods.setId(4);
        goods.setName("《郭源潮》");
        goods.setPrice(150);
        goods.setAmount(3);
        goodsService.addGoods(goods);

        String requestBody = "{\"id\": 4,\"name\":\"《范特西》\",\"price\":300,\"amount\": 10}";
        this.mockMvc.perform(put("/goods/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andDo(print())
                                .andExpect(status().isOk());

        assertEquals(300,goodsService.searchGoodsById(4).getPrice());
    }

    //对delete方法的测试
    @Test
    public void deleteTest() throws Exception {
        Goods goods=new Goods();
        goods.setId(5);
        goods.setName("《空港曲》");
        goods.setPrice(200);
        goods.setAmount(1);
        goodsService.addGoods(goods);

        this.mockMvc.perform(delete("/goods/5"))
                                .andDo(print())
                                .andExpect(status().isOk());

        Assertions.assertNull(goodsService.searchGoodsById(5));
    }
}