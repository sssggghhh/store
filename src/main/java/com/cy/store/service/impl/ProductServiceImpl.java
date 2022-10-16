package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.ProductNotFoundException;
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

    /**
     * 热销商品排行
     * @return
     */
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

    /**
     * 根据商品id查询商品详情
     * @param id
     * @return
     */
    @Override
    public Product findProductById(Integer id) {
        Product product = productMapper.findProductById(id);
        if (product == null){
            throw new ProductNotFoundException("商品信息不存在...");
        }
        // 将查询结果中的部分属性设置为null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;

    }


}
