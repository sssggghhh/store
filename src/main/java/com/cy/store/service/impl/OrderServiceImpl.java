package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 20 - 10:35
 * @Description: 订单业务实现类
 * @version: 1.0
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Transactional
    @Override
    public Order createOrder(Integer aid, Integer[] cids, Integer uid, String username) {
        // 创建当前时间对象
        Date date = new Date();
        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVO> cartVOByCids = cartService.getCartVOByCids(uid,cids);
        // 计算这些商品的总价
        long totalPrice = 0;
        for (CartVO cartVOByCid : cartVOByCids) {
            totalPrice += cartVOByCid.getPrice() * cartVOByCid.getNum();
        }
        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        // 查询收货地址数据
        Address addressByAid = addressService.getAddressByAid(aid, uid);
        // 补全数据：收货地址相关的6项
        order.setRecvName(addressByAid.getName());
        order.setRecvPhone(addressByAid.getPhone());
        order.setRecvProvince(addressByAid.getProvinceName());
        order.setRecvCity(addressByAid.getCityName());
        order.setRecvArea(addressByAid.getAreaName());
        order.setRecvAddress(addressByAid.getAddress());
        // 补全数据：totalPrice
        order.setTotalPrice(totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(date);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(date);
        order.setModifiedUser(username);
        order.setModifiedTime(date);
        // 插入订单数据
        Integer integer = orderMapper.insertOrder(order);
        if (integer != 1){
            throw new InsertException("插入订单数据异常！");
        }
        // 遍历carts，循环插入订单商品数据
        for (CartVO cartVOByCid : cartVOByCids) {
            // 创建订单商品数据
            OrderItem orderItem = new OrderItem();
            // 补全数据：oid(order.getOid())
            orderItem.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            orderItem.setPid(cartVOByCid.getPid());
            orderItem.setTitle(cartVOByCid.getTitle());
            orderItem.setImage(cartVOByCid.getImage());
            orderItem.setPrice(cartVOByCid.getPrice());
            orderItem.setNum(cartVOByCid.getNum());
            // 补全数据：4项日志
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(date);
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(date);
            // 插入订单商品数据
            Integer integer1 = orderMapper.insertOrderItem(orderItem);
            if (integer1 != 1){
                throw new InsertException("插入订单商品数据异常！！！");
            }
        }
        // 返回
        return order;
    }
}
