package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//@RunWith：表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserServiceTests {

    //如果漂红，是因为idea有检测的功能，接口是不能够直接创建Bean的，Mybatis是通过动态代理技术来解决的，
    @Autowired
    private IUserService iUserService;

    /**
     * 单位测试方法：就可以单独运行，不用启动整个项目，可以做单位测试，提升了代码的测试效率
     * 1、必须被@Test注解修饰
     * 2、返回值类型必须是void
     * 3、方法的参数列表不指定任何类型
     * 4、方法的访问修饰符必须是public
     */
    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("xiaowangba");
            user.setPassword("123");
            iUserService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass().getSimpleName());
        }
    }

    @Test
    public void login(){
        User user = iUserService.login("sgh", "123");

        System.out.println(user);
    }

    @Test
    public void changePassword(){
        iUserService.changePassword(8,"gh","13","321");
    }

    @Test
    public void getByUid(){
        System.out.println(iUserService.getUserByUid(6));
    }

    @Test
    public void changeUserInfo(){
        User user = iUserService.getUserByUid(5);
        user.setPhone("1234567890");
        user.setEmail("123@qq.com");
        user.setGender(1);
        iUserService.changeUserInfo(5,"xiaobiaozi",user);
    }

    @Test
    public void changeUserAvatar(){
        iUserService.changeUserAvatar(7,"hahahha.png","管理员");
    }
}
