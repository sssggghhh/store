package com.cy.store.controller;

import com.cy.store.entity.Product;
import com.cy.store.service.IProductService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 09 - 07 - 22:49
 * @Description: com.cy.store.controller
 * @Vsersion: 1.0
 */
@RestController
@RequestMapping("products")
public class ProductController extends BaseController{

    @Autowired
    private IProductService iProductService;

    /**
     * 获取热销前四名的商品
     * @return
     */
    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotProduct(){
        List<Product> hotProduct = iProductService.findHotProduct();
        return new JsonResult<List<Product>>(OK,hotProduct);
    }

    /**
     * 根据商品id查询商品详情
     * @param id
     * @return
     */
    @GetMapping("{id}/details")
    public JsonResult<Product> getProductById(@PathVariable("id") Integer id){
        Product product = iProductService.findProductById(id);
        return new JsonResult<Product>(OK,product);
    }
}
