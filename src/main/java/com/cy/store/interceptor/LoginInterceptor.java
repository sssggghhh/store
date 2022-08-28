package com.cy.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 检测全局session是否有uid数据，如果有则放行，如果没有重定向到login.html页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url+Controller，映射）
     * @return 如果返回值为TRUE则放行请求，如果返回值为Fasle则标志拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HttpServletRequest对象来获取session对象，再获取session中的值
        Object o = request.getSession().getAttribute("uid");
        //如果值为空则表示用户没有登录过系统，需要跳转到登录页面
        if (o == null){
            response.sendRedirect("/web/login.html");
            //结束后续调用
            return false;
        }
        //放行请求
        return true;
    }
}
