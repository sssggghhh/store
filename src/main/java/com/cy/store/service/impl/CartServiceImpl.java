package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 20 - 17:27
 * @Description: com.cy.store.service.impl
 * @Vsersion: 1.0
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService iProductService;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer num, String username) {
        // 根据参数pid和uid查询购物车中的数据
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date now  = new Date();
        // 判断查询结果是否为null 是：表示该用户并未将该商品添加到购物车
        if (result == null){
            // -- 创建Cart对象
            Cart cart = new Cart();
            // -- 封装数据：uid,pid,num
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(num);
            // -- 调用productService.findById(pid)查询商品数据，得到商品价格
            Product product = iProductService.findProductById(pid);
            // -- 封装数据：price
            cart.setPrice(product.getPrice());
            // -- 封装数据：4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);
            // -- 调用insert(cart)执行将数据插入到数据表中
            Integer integer = cartMapper.insertCart(cart);
            if (integer != 1){
                 throw new InsertException("向购物车插入商品数据时出现错误！");
            }
        }else {// 否：表示该用户的购物车中已有该商品
            // -- 从查询结果中获取购物车数据的id
            Integer cid = result.getCid();
            // -- 从查询结果中取出原数量，与参数amount相加，得到新的数量
            Integer newNum = result.getNum() + num;
            // -- 执行更新数量
            Integer integer = cartMapper.updateNumByCid(cid, newNum, username, now);
            if (integer != 1){
                throw new UpdateException("更新购物车商品数量时出错！");
            }
        }
    }

    @Override
    public List<CartVO> getCartVOByUid(Integer uid) {
        List<CartVO> cartVOByUid = cartMapper.findCartVOByUid(uid);
        return cartVOByUid;
    }
}
