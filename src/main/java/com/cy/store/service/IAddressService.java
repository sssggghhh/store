package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 20 - 17:21
 * @Description: com.cy.store.service
 * @Vsersion: 1.0
 */
public interface IAddressService {
    /**
     * 添加新的收货地址
     * @param uid
     * @param username
     * @param address
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 根据用户的uid查询用户的收货地址列表
     * @param uid
     * @return
     */
    List<Address> getAddressByUid(Integer uid);


    /**
     * 设置默认收货地址
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 当前登录用户
     */
    void setDefaultAddress(Integer aid,Integer uid,String username);


    /**
     * 删除收货地址
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 当前登录用户
     */
    void deleteAddressByAid(Integer aid,Integer uid,String username);

}
