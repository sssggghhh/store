package com.cy.store.config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器拦截器的注册
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    /**
     * 配置拦截器
      * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器对象
        HandlerInterceptor loginInterceptor = new LoginInterceptor();
        //配置白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/login.html");
        patterns.add("/web/register.html");
        patterns.add("/web/product.html");
        patterns.add("/users/login");
        patterns.add("/users/reg");
        patterns.add("/districts/**");
        //完成拦截器的注册
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns(patterns);

    }
}
