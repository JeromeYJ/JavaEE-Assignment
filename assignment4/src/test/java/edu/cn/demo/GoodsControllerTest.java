package edu.cn.demo;

import edu.cn.demo.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GoodsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoodsService goodsService;

    //对get方法的测试
    @Test
    public void getTest() throws Exception {
        this.mockMvc.perform(get("/goods/1"))
                                .andDo(print())
                                .andExpect(status().isNoContent());
    }

    //对post方法的测试（这里还有疑问）
    @Test
    public void postTest() throws Exception {
        String requestBody = "{\"id\": 1,\"name\":\"《安河桥北》\",\"price\":150,\"amount\": 2}";
        this.mockMvc.perform(post("/goods")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andDo(print())
                                .andExpect(status().isOk());

        requestBody = "{\"id\": 2,\"name\":\"《-》\",\"price\":200,\"amount\": 1}";
        this.mockMvc.perform(post("/goods")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andDo(print())
                                .andExpect(status().isOk());
    }

    //对put方法的测试
    @Test
    public void putTest() throws Exception {
        String requestBody = "{\"id\": 2,\"name\":\"《范特西》\",\"price\":300,\"amount\": 10}";
        this.mockMvc.perform(put("/goods/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andDo(print())
                                .andExpect(status().isNoContent());
    }

    //对delete方法的测试
    @Test
    public void deleteTest() throws Exception {
        String requestBody = "{\"id\": 2,\"name\":\"《范特西》\",\"price\":300,\"amount\": 10}";
        this.mockMvc.perform(put("/goods/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andDo(print())
                                .andExpect(status().isNoContent());
    }
}