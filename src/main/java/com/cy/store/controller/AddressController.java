package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 20 - 18:21
 * @Description: com.cy.store.controller
 * @Vsersion: 1.0
 */

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    /**
     * 新增收货地址
     * @param address
     * @param httpSession
     * @return
     */
    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession httpSession){
        //从session中获取用户的uid和username
        Integer uid = getuidFromSession(httpSession);
        String username = getusernameFromSession(httpSession);

        addressService.addNewAddress(uid,username,address);

        return new JsonResult<Void>(OK);
    }

    /**
     * 查询收货地址列表
     * @param session
     * @return
     */
    @GetMapping({"","/"})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<Address> list = addressService.getAddressByUid(uid);
        return new JsonResult<>(OK,list);
    }

    /**
     * 设置默认收货地址
     * @return
     */
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefaultAddress(@PathVariable("aid") Integer aid,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        addressService.setDefaultAddress(aid,uid,username);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        addressService.deleteAddressByAid(aid,uid,username);
        return new JsonResult<Void>(OK);
    }
}
