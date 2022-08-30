package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 30 - 16:58
 * @Description: 商品信息mapper接口
 * @version: 1.0
 */
public interface ProductMapper {

    /**
     * 查询热销商品
     * @return
     */
    List<Product> findHotProduct();
}
