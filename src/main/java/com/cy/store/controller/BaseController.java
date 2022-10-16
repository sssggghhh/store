package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层的基类
 */
public class BaseController {
    //操作成功的状态码
    public static final int OK = 200;

    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //@ExceptionHandler 当项目中产生了异常，被统一拦截到此方法中，这个方法充当的就是请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler(ServiceException.class)//用于统一处理抛出的异常
    public JsonResult<Void> handlerExcetion(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(5001);
            result.setMessage("密码错误");
        }else if(e instanceof UsernameNotFoundException){
            result.setState(5002);
            result.setMessage("用户不存在");
        }else if(e instanceof InsertException){
            result.setState(5003);
        }else if(e instanceof UpdateException){
            result.setState(5004);
        }else if(e instanceof DeleteException){
            result.setState(5005);
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件为空异常");
        }else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件大小异常");
        }else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("文件类型异常");
        }else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("文件状态异常");
        }else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("文件IO异常");
        }else if (e instanceof AddressCountLimitException){
            result.setState(7001);
            result.setMessage("插入收货地址异常");
        }else if (e instanceof AddressNotFoundException) {
        result.setState(7002);
        result.setMessage("收货地址查询异常");
         }else if (e instanceof AddressDeniedException){
        result.setState(7003);
        result.setMessage("非法访问收货地址异常");
         }else if (e instanceof ProductNotFoundException){
            result.setState(8001);
            result.setMessage("商品信息不存在异常");
         }

        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录对象的uid
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取session对象中的username
     * @param session session
     * @return 当前登录对象的username
     */
    protected final String getusernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
