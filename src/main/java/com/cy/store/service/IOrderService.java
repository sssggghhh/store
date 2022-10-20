package com.cy.store.service;

import com.cy.store.entity.Order;


/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 20 - 10:28
 * @Description: 订单业务处理接口
 * @version: 1.0
 */

public interface IOrderService {
    /**
     * 创建订单
     * @param aid
     * @param cids
     * @param uid
     * @param username
     * @return 成功创建的订单数据
     */
    Order createOrder(Integer aid, Integer[] cids, Integer uid, String username);
}
