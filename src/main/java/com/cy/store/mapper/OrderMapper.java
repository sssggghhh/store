package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;

/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 19 - 16:10
 * @Description: 订单持久层接口
 * @version: 1.0
 */
public interface OrderMapper {
    /**
     * 向Order表中插入订单数据
     * @param order
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 向OrderItem表中插入订单中的商品数据
     * @param orderItem
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);
}
