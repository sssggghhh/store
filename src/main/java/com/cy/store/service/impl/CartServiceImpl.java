package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
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

    /**
     * 向购物车中添加商品
     * @param uid 用户ID
     * @param pid 商品ID
     * @param num 添加商品的数量
     * @param username 登录用户的用户名
     */
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

    /**
     * 查看购物车中的商品信息
     * @param uid
     * @return
     */
    @Override
    public List<CartVO> getCartVOByUid(Integer uid) {
        List<CartVO> cartVOByUid = cartMapper.findCartVOByUid(uid);
        return cartVOByUid;
    }

    /**
     * 增加购物车中商品的数量
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    @Override
    public Integer addNum(Integer cid, Integer uid, String username){
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart cartByCid = cartMapper.findCartByCid(cid);
        // 判断查询结果是否为null
        if (cartByCid == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("该购物车数据不存在!");
        }
        // 判断查询结果中的uid与参数uid是否不一致
        if (!cartByCid.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            try {
                throw new AccessDeniedException("非法访问!");
            } catch (AccessDeniedException e) {
                e.printStackTrace();
            }
        }
        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = cartByCid.getNum()+1;

        // 创建当前时间对象，作为modifiedTime
        Date date = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer integer = cartMapper.updateNumByCid(cid, num, username, date);
        if (integer != 1){
            throw new InsertException("修改商品数量时出现未知错误!");
        }
        return num;
    }
}
