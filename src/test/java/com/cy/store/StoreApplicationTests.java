package com.cy.store;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void insert(){
        User user = new User();
        user.setUsername("xiaoshuai");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);

    }

}
