package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 09 - 07 - 22:29
 * @Description: 商品业务层实现类
 * @Vsersion: 1.0
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findHotProduct() {
        List<Product> hotProduct = productMapper.findHotProduct();
        //不必要的信息不返回
        for (Product product : hotProduct) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return hotProduct;
    }
}
