package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
//@RunWith：表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("xiaohei");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("xiaohei");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(5,"123","管理员",new Date());
    };

    @Test
    public void findUserByUid(){
        System.out.println(userMapper.findUserByUid(6));
    }

    @Test
    public void updateUserInfoByUid(){
        User user = new User();
        user.setPhone("18500400097");
        user.setEmail("cqm95h@163.com");
        user.setGender(1);
        user.setUid(6);
        user.setModifiedUser("管理员");
        user.setModifiedTime(new Date());
        Integer integer = userMapper.updateUserInfoByUid(user);
        System.out.println(integer);
    }

    @Test
    public void updateUserAvatarByUid(){
        Integer integer = userMapper.updateUserAvatarByUid(6, "update.png", "管理员", new Date());
        System.out.println(integer);
    }

}
