package com.cy.store.service;


import com.cy.store.vo.CartVO;

import java.util.List;

/**
 * 用户插入接口
 */
public interface ICartService {

    /**
     * 向购物车中添加信息
     * @param uid 用户ID
     * @param pid 商品ID
     * @param num 添加商品的数量
     * @param username 登录用户的用户名
     */
    void addToCart(Integer uid,Integer pid,Integer num,String username);

    /**
     * 根据用户uid获取该用户的购物车商品信息
     * @param uid
     * @return
     */
    List<CartVO> getCartVOByUid(Integer uid);
}
