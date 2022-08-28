package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 20 - 18:01
 * @Description: com.cy.store.service
 * @Vsersion: 1.0
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService iAddressService;

    @Test
    public void addNewAddress(){
        try {
            Integer uid = 21;
            String username = "管理员";
            Address address = new Address();
            address.setName("张三");
            address.setPhone("17858805555");
            address.setAddress("雁塔区小寨华旗");
            iAddressService.addNewAddress(uid, username, address);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void setDefaultAddress(){
        try {
            Integer aid = 1;
            Integer uid = 6;
            String username = "系统管理员";
            iAddressService.setDefaultAddress(aid, uid, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
