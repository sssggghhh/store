package com.cy.store.mapper;

import com.cy.store.entity.User;

import java.util.Date;

/**
 * 用户模块的持久层接口
 */

public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户数据
     * @return 返回受影响的行数（增、删、改，都受影响的行数作为返回值，可以根据返回值来判断是否执行成功）
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据，如果没有找到则返回null
     */
    User findByUsername(String username);

    /**
     * 根据用户的uid来修改用户密码
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户uid来查询用户信息
     * @param uid
     * @return
     */
    User findUserByUid(Integer uid);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Integer updateUserInfoByUid(User user);

    /**
     * 上传、修改用户头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateUserAvatarByUid(Integer uid,String avatar,String modifiedUser,Date modifiedTime);

}
