package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.service.IOrderService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 20 - 16:50
 * @Description:
 * @version: 1.0
 */
@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;

    public JsonResult<Order> createOrder(Integer aid, Integer[] cids, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        Order order = orderService.createOrder(aid, cids, uid, username);
        return new JsonResult<Order>(OK,order);
    }
}
