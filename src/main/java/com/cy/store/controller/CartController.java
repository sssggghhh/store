package com.cy.store.controller;

import com.cy.store.mapper.CartMapper;
import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 17 - 15:10
 * @Description:
 * @version: 1.0
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController{
    @Autowired
    private ICartService iCartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer num, HttpSession session){
        //从session中获取用户ID和用户名
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        iCartService.addToCart(uid,pid,num,username);
        //返回成功
        return new JsonResult<Void>(OK);
    }
}
