package com.cy.store.controller;

import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping({"","/"})
    public JsonResult<List<CartVO>> getCartVOByUid(HttpSession session){
        //从Session中获取用户的uid
        Integer uid = getuidFromSession(session);
        List<CartVO> list = iCartService.getCartVOByUid(uid);
        return new JsonResult<List<CartVO>>(OK,list);
    }
}
