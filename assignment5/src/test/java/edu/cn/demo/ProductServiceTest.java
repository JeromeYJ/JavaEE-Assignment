package edu.cn.demo;

import edu.cn.demo.domain.Product;
import edu.cn.demo.domain.ProductDto;
import edu.cn.demo.domain.Product_supplier;
import edu.cn.demo.domain.Supplier;
import edu.cn.demo.service.impl.ProductServiceImpl;
import edu.cn.demo.service.impl.Product_supplierServiceImpl;
import edu.cn.demo.service.impl.SupplierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductServiceImpl service;

    @Autowired
    SupplierServiceImpl supplierService;

    @Autowired
    Product_supplierServiceImpl product_supplierService;



    @BeforeEach
    public void init(){
        Product product = new Product();
        //product.setId(1);
        product.setName("《郭源潮》");
        product.setPrice(300.0);
        product.setAmount(3);
        service.save(product);

        Product_supplier product_supplier1 = new Product_supplier();
        Product_supplier product_supplier2 = new Product_supplier();
        Product_supplier product_supplier3 = new Product_supplier();
        Integer id = service.searchByName("《郭源潮》").getId();

        Supplier supplier1 = new Supplier();
        supplier1.setName("company1");
        supplier1.setAddress("二仙桥");

        Supplier supplier2 = new Supplier();
        supplier2.setName("company2");
        supplier2.setAddress("成华大道");

        Supplier supplier3 = new Supplier();
        supplier3.setName("company3");
        supplier3.setAddress("成华大道");

        supplierService.save(supplier1);
        supplierService.save(supplier2);
        supplierService.save(supplier3);

        product_supplier1.setProduct_id(id);
        product_supplier1.setSupplier_id(supplier1.getId());
        product_supplier2.setProduct_id(id);
        product_supplier2.setSupplier_id(supplier2.getId());
        product_supplier3.setProduct_id(id);
        product_supplier3.setSupplier_id(supplier3.getId());


        product_supplierService.save(product_supplier1);
        product_supplierService.save(product_supplier2);
        product_supplierService.save(product_supplier3);
    }


    @Test
    public void getTest() {
        Product product = new Product();
        product.setName("《范特西》");
        product.setPrice(400.0);
        product.setAmount(2);
        service.save(product);

        Product product1 = service.searchById(product.getId());
        Product product2 = service.searchByName(product.getName());
        Product product3 = service.searchByName("《郭源潮》");
        List<Product> product4 = service.searchByPrice(product.getPrice());
        List<Product> product5 = service.searchByPriceRange(200.0, 500.0);
        List<Product> product6 = service.searchByPriceRange(500.0, 1000.0);
        List<Product> product7 = service.searchByAmount(3);
        List<Product> product8 = service.searchByAmountRange(1,3);

        assertNotNull(product1);
        assertNotNull(product2);
        assertNotNull(product3);
        assertNotNull(product4);
        assertFalse(product5.isEmpty());
        assertEquals(2, product5.size());
        assertTrue(product6.isEmpty());
        assertFalse(product7.isEmpty());
        assertEquals(1, product8.size());

        Integer id = service.searchByName("《郭源潮》").getId();

        List<Product_supplier> list = product_supplierService.searchByProductId(id);
        assertEquals(3, list.size());
    }

    @Test
    public void addTest() {
        Product product = new Product();
        product.setName("《exile》");
        product.setPrice(700.0);
        product.setAmount(1);

        boolean flag = service.add(product);
        Product product1 = service.searchById(product.getId());

        assertTrue(flag);
        assertNotNull(product1);
    }


    @Test
    public void changeTest() {
        Product product = new Product();
        product.setName("《丑奴儿》");
        product.setPrice(200.0);
        product.setAmount(10);
        service.save(product);

        product.setAmount(100);
        service.change(product);

        Product product1 = service.searchById(product.getId());
        assertNotNull(product1);
        assertEquals(100, product1.getAmount());
    }


    @Test
    public void deleteTest() {
        Product product = new Product();
        product.setName("《Tim》");
        product.setPrice(500.0);
        product.setAmount(2);
        service.save(product);

        Integer id = product.getId();
        service.delete(id);

        assertNull(service.getById(id));
        assertNull(service.searchByName("《Tim》"));
    }

}