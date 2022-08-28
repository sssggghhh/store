package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 用户模块业务层的实现类
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {

        String username = user.getUsername();
        //调用findByUsername()判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        //判断结果集是否不为null则抛出用户名被占用的异常
        if (result != null){
            throw new UsernameDuplicatedException("用户名被占用！！！");
        }

        String oldPassword = user.getPassword();

        String salt = UUID.randomUUID().toString().toUpperCase();

        user.setSalt(salt);

        String md5Password = getMD5Password(oldPassword, salt);

        user.setPassword(md5Password);

        //补全数据：is_Delete、4个日志字段信息
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行插入操作  rows==1表示插入成功
        Integer rows = userMapper.insert(user);

        if (rows != 1){
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {

        User result = userMapper.findByUsername(username);

        if (result == null){
            throw new UsernameDuplicatedException("用户名不存在！！！");
        }

        if (result.getIsDelete() == 1){
            throw new UsernameNotFoundException("用户不存在！！！");
        }

        String salt = result.getSalt();

        String md5Password = getMD5Password(password, salt);

        String resultPassword = result.getPassword();

        if (!resultPassword.equals(md5Password)){
            throw new PasswordNotMatchException("用户名或密码错误！！！");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        //根据uid获取用户信息
        User user = userMapper.findUserByUid(uid);

        //判断用户是否存在或是否被删除
        if(user == null || user.getIsDelete() == 1){
            throw new UsernameNotFoundException("用户不存在！！！");
        }

        //判断用户输入的密码是否与原密码一致
        String salt = user.getSalt();

        String md5Password = getMD5Password(oldPassword, salt);

        if(!user.getPassword().equals(md5Password)){
            throw new PasswordNotMatchException("密码错误！！！");
        }
        //执行修改密码逻辑
        String newMd5Password = getMD5Password(newPassword, salt);

        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());

        if(rows != 1){
            throw new UpdateException("修改密码产生了未知异常！！！");
        }

    }

    @Override
    public User getUserByUid(Integer uid) {
        User user = userMapper.findUserByUid(uid);
        if(user == null || user.getIsDelete() == 1){
            throw new UsernameNotFoundException("用户不存在！！！");
        }

        //因为前端只需要展示4个字段的值，如果直接将完整的user对象返回，数据太重量了，所以可以创建一个新的user对象中间过度一下
        User showUser = new User();
        showUser.setUsername(user.getUsername());
        showUser.setPhone(user.getPhone());
        showUser.setEmail(user.getEmail());
        showUser.setGender(user.getGender());

        return showUser;


    }

    @Override
    public void changeUserInfo(Integer uid, String username, User user) {

        User showUser = userMapper.findUserByUid(uid);

        if (showUser == null || showUser.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户不存在！！！");
        }

        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer integer = userMapper.updateUserInfoByUid(user);

        if (integer != 1) {
            throw new UpdateException("修改用户信息发生未知异常！！！");
        }

    }

    @Override
    public void changeUserAvatar(Integer uid, String avatar, String username) {
        User user = userMapper.findUserByUid(uid);
        if (user == null || user.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户不存在！！！");
        }
        Integer integer = userMapper.updateUserAvatarByUid(uid, avatar, username, new Date());

        if (integer != 1) {
            throw new UpdateException("更新头像时出现异常！！！");
        }
    }

    private String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password).getBytes()).toUpperCase();
        }
        return password;

    }
}
