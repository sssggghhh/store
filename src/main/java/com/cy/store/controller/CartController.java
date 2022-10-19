package com.cy.store.controller;

import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 将商品添加到购物车
     * @param pid
     * @param amount
     * @param session
     * @return
     */
    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session){
        //从session中获取用户ID和用户名
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        iCartService.addToCart(uid,pid,amount,username);
        //返回成功
        return new JsonResult<Void>(OK);
    }

    /**
     * 查看购物车中的商品信息
     * @param session
     * @return
     */
    @GetMapping({"","/"})
    public JsonResult<List<CartVO>> getCartVOByUid(HttpSession session){
        //从Session中获取用户的uid
        Integer uid = getuidFromSession(session);
        List<CartVO> list = iCartService.getCartVOByUid(uid);
        return new JsonResult<List<CartVO>>(OK,list);
    }


    /**
     * 增加购物车中商品的数量
     * @param cid
     * @param session
     * @return
     */
    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        Integer data = iCartService.addNum(cid, uid, username);
        return new JsonResult<Integer>(OK,data);
    }

    /**
     * 根据前台界面传递过来的购物车id查询商品
     * @param cids
     * @param session
     * @return
     */
    @GetMapping("list")
    public JsonResult<List<CartVO>> getCartVOByCids(Integer[] cids,HttpSession session){
        Integer uid = getuidFromSession(session);
        List<CartVO> cartVOByCids = iCartService.getCartVOByCids(uid, cids);
        return new JsonResult<List<CartVO>>(OK,cartVOByCids);
    }
}
