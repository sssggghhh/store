package com.cy.store.service;

import com.cy.store.entity.User;


/**
 * 用户插入接口
 */
public interface IUserService {
    /**
     * 用户注册
     * @param user
     */
    void reg(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 当前匹配的User对象
     */
    User login(String username,String  password);

    /**
     * 修改密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    /**
     * 根据uid获取用户信息
     * @param uid
     * @return
     */
    User getUserByUid(Integer uid);

    /**
     * 修改用户信息
     * @param uid 当前用户的uid
     * @param username 当前用户的username
     * @param user 用户的新数据
     */
    void changeUserInfo(Integer uid,String username,User user);

    /**
     * 修改用户头像
     * @param uid
     * @param avatar
     * @param username
     */
    void changeUserAvatar(Integer uid,String avatar,String username);
}
