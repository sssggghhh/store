package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController //@Controller + @ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);

        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);

        //向session对象中完成数据的绑定（session是全局的）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        System.out.println(getuidFromSession(session));
        System.out.println(getusernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }

    //修改用户密码
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        //因为修改密码必须是在登录的状态下，登录之后会将用户的uid和username存在session中
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<Void>(OK);
    }

    //根据uid获取用户的信息
    @GetMapping("get_by_uid")
    public JsonResult<User> getUserInfoByUid(HttpSession session){
        //从session中获取用户的信息
        User user = userService.getUserByUid(getuidFromSession(session));
        return new JsonResult<User>(OK,user);
    }

    //修改用户资料
    @RequestMapping("change_info")
    public JsonResult<Void> changeUserInfo(HttpSession session,User user){
        //修改信息需要四个字段，username，phone，email，gender，所以user对象需要包含这四个字段的信息
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        userService.changeUserInfo(uid,username,user);
        return new JsonResult<Void>(OK);
    }

    /**
     * MultipartFile接口是SpringMVC提供的一个接口，这个接口为我们包装了回去文件类型
     * 的数据（任何数据类型的file都可以接收），SpringBoot他又整合了SpringMVC，只
     * 需要在处理请求的方法参数列表上声明一个参数类型为MultipartFile的参数，然后
     * SpringBoot自动将传递给服务的文件数据赋值给这个参数
     *  @RequestParm 表示请求中的参数，将请求中的参数注入请求处理方法的某个参数上，
     *  如果名称不一致则可以使用@RequestParm注解进行标记个映射
     * @param session
     * @param file
     * @return
     */
    //头像文件大小的上限值
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //允许上传的头像的文件类型
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    //初始化允许上传的头像的文件类型
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }
    //修改用户头像
    @RequestMapping("change_avatar")
    public JsonResult<String> changeUserAvatar(HttpSession session, MultipartFile file){
        //判断上传的文件是否为空
        if (file.isEmpty()) {
            throw new FileEmptyException("上传的头像文件不允许为空！！！");
        }
        //判断上传的文件大小是否超出限制
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE /1024) + "KB的头像文件");
        }
        //判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        if (!AVATAR_TYPES.contains(contentType)) {
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型为："+AVATAR_TYPES);
        }

        //获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        System.out.println(parent);
        //保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }

        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        //创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        //执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            //抛出异常
            throw new FileStateException("文件状态异常，可能文件已经被移动或删除");
        }catch (IOException e){
            throw new FileUploadIOException("上传文件时读写错误，请稍后重试");
        }

        //头像路径
        String avatar = "/upload/" + filename;
        //从Session中获取uid和username
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        //将头像写入到数据库中
        userService.changeUserAvatar(uid,avatar,username);
        //返回成功头像路径
        return  new JsonResult<String>(OK,avatar);

    }


}
