package com.cy.store.service;

import com.cy.store.entity.Product;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 09 - 07 - 22:22
 * @Description: 商品接口
 * @Vsersion: 1.0
 */
public interface IProductService {

    /**
     * 查询销量最高的前四名商品
     * @return
     */
    List<Product> findHotProduct();
}
