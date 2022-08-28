package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//@RunWith：表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insertAddress(){
        Address address = new Address();
        address.setUid(6);
        address.setPhone("1234567890");
        address.setAddress("郑州");
        address.setName("sgh");
        Integer integer = addressMapper.insertAddress(address);
    }

    @Test
    public void countAddressByUid(){
        Integer integer = addressMapper.countByUid(6);
        System.out.println(integer);
    }
}
