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
     * @return 返回购物车中数据
     */
    List<CartVO> getCartVOByUid(Integer uid);

    /**
     * 将购物车中某商品的数量加1
     * @param cid
     * @param uid
     * @param username
     * @return 返回修改后的商品数量
     */
    Integer addNum(Integer cid,Integer uid,String username);

    /**
     * 根据若干个购物车数据id查询想详情的列表
     * @param uid
     * @param cids
     * @return  匹配的购物车数据详情的列表
     */
    List<CartVO> getCartVOByCids(Integer uid,Integer[] cids);
}
