package edu.cn.demo;

import edu.cn.demo.aspect.ControllerAspect;
import edu.cn.demo.controller.ProductController;
import edu.cn.demo.domain.Product;
import edu.cn.demo.exception.ProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AspectTest {

    @Autowired
    ProductController productController;

    @Autowired
    ControllerAspect controllerAspect;

    @BeforeEach
    public void init() throws ProductException {
        Product product = new Product();
        product.setName("《郭源潮》");
        product.setPrice(300.0);
        product.setAmount(3);

        productController.add(product);
    }

    @Test
    public void apiCallingTimesTest() throws ProductException {
        productController.getByName("《郭源潮》");

        Product product = new Product();
        product.setName("《范特西》");
        product.setPrice(400.0);
        product.setAmount(2);
        productController.add(product);

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
    public void apiTimeTest() throws ProductException {
        productController.getByName("《郭源潮》");

        Product product = new Product();
        product.setName("《范特西》");
        product.setPrice(400.0);
        product.setAmount(2);
        productController.add(product);

        productController.getByName("《范特西》");

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
    public void apiExceptionTest() throws ProductException {
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
