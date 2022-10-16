package com.cy.store.service;

import com.cy.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 09 - 07 - 22:38
 * @Description: com.cy.store.service
 * @Vsersion: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    private IProductService iProductService;

    @Test
    public void testFindHotProduct(){
        List<Product> hotProduct = iProductService.findHotProduct();
        for (Product product : hotProduct) {
            System.out.println(product);
        }
    }

    @Test
    public void testFindProductById(){
        Product product = iProductService.findProductById(10000037);
        System.out.println(product);
    }
}
