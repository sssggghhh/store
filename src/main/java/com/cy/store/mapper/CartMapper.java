package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 16 - 17:22
 * @Description: com.cy.store.mapper
 * @Vsersion: 1.0
 */
public interface CartMapper {

    /**
     * 将商品插入购物车信息
     * @param cart
     * @return
     */
    Integer insertCart(Cart cart);

    /**
     * 更新购物车数据中商品的数量
     * @param cid 购物车数据的id
     * @param num 新的数量
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(@Param("cid") Integer cid,
                           @Param("num") Integer num,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户id和商品id查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车中的数据，如果该用户的购物车中并没有该商品，则返回null
     */
    Cart findByUidAndPid(@Param("uid") Integer uid,@Param("pid") Integer pid);

    /**
     * 根据用户id查询用户的购物车数据
     * @param uid
     * @return 该用户的购物车列表数据
     */
    List<CartVO> findCartVOByUid(Integer uid);

    /**
     * 根据购物车cid查询购物车中的数据
     * @param cid
     * @return
     */
    Cart findCartByCid(Integer cid);

    /**
     * 根据若干个购物车数据id查询商品详情的列表
     * @param cids
     * @return 返回选择的购物车数据
     */
    List<CartVO> findCartVOByCids(Integer[] cids);

}
