package edu.cn.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cn.demo.aspect.ControllerAspect;
import edu.cn.demo.controller.ProductController;
import edu.cn.demo.domain.Product;
import edu.cn.demo.exception.ProductException;
import edu.cn.demo.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AspectTest {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    ProductController productController;

    @Autowired
    ControllerAspect controllerAspect;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void init() throws Exception {
        Product product = new Product();
        product.setName("《郭源潮》");
        product.setPrice(300.0);
        product.setAmount(3);

        productService.add(product);
    }

    @Test
    public void apiCallingTimesTest() throws Exception {
        //productController.getByName("《郭源潮》");
        mockMvc.perform(get("/product/name?name=《郭源潮》"))
                            .andExpect(status().isOk());


        Product product1 = new Product();
        product1.setName("《范特西》");
        product1.setPrice(400.0);
        product1.setAmount(2);

        Product product2 = new Product();
        product2.setName("《 》");
        product2.setPrice(1000000.0);
        product2.setAmount(1);
        //productController.add(product);
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product1)))
                        .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product2)))
                        .andExpect(status().isOk());

        System.out.println();
        controllerAspect.getCallingTimes().forEach((key,value)->{
            System.out.println("Calling times:" + key + " = " + value);
        });
        System.out.println();

        String add = "Product edu.cn.demo.controller.ProductController.add(Product)";
        String getByName = "Product edu.cn.demo.controller.ProductController.getByName(String)";

        assertEquals(2, controllerAspect.getCallingTimes().get(add));
        assertEquals(1, controllerAspect.getCallingTimes().get(getByName));
    }

    @Test
    public void apiTimeTest() throws Exception {
        //productController.getByName("《郭源潮》");
        mockMvc.perform(get("/product/name?name=《郭源潮》"))
                            .andExpect(status().isOk());

        Product product = new Product();
        product.setName("《范特西》");
        product.setPrice(400.0);
        product.setAmount(2);
        //productController.add(product);
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                        .andExpect(status().isOk());

        //productController.getByName("《范特西》");
        mockMvc.perform(get("/product/name?name=《范特西》"))
                        .andExpect(status().isOk());

        System.out.println();
        controllerAspect.getCallingTimes().forEach((key,value)->{
            System.out.println("Calling times:" + key + " = " + value);
        });
        System.out.println();

        System.out.println();
        controllerAspect.getLongestTime().forEach((key,value)->{
            System.out.println("Longest response time:" + key + " = " + value);
        });
        System.out.println();
        controllerAspect.getShortestTime().forEach((key,value)->{
            System.out.println("Shortest response time:" + key + " = " + value);
        });
        System.out.println();
        controllerAspect.getAvgTime().forEach((key,value)->{
            System.out.println("Average response time:" + key + " = " + value);
        });
        System.out.println();
    }

    @Test
    public void apiExceptionTest() throws Exception {
        Product product1 = new Product();
        product1.setName("《Tim》");
        product1.setPrice(500.0);
        product1.setAmount(2);
        productController.add(product1);

        Product product2 = new Product();
        product2.setName("《Avicii》");
        product2.setPrice(300.0);
        product2.setAmount(2);
        productController.add(product2);

        Integer id1 = product1.getId();
        Integer id2 = product2.getId();
        productController.delete(id1);
        productController.delete(id2);

        assertThrows(ProductException.class, ()-> productController.getById(id1));
        assertThrows(ProductException.class, ()-> productController.getById(id2));
        assertThrows(ProductException.class, ()-> productController.getByName("《Tim》"));

        System.out.println();
        controllerAspect.getExceptionTimes().forEach((key,value)->{
            System.out.println("Exception times:" + key + " = " + value);
        });
        System.out.println();
    }
}
