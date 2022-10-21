package com.cy.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 21 - 10:53
 * @Description:
 * @version: 1.0
 */

@Aspect
@Component
public class TimerAspect {
    @Around("execution(* com.cy.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //记录起始时间
        long start = System.currentTimeMillis();
        //执行连接点方法，即切面所在位置对应的方法。本项目中表示执行注册或执行登录等
        Object result = pjp.proceed();
        //记录结束时间
        long end = System.currentTimeMillis();
        //计算耗时
        System.out.println("耗时："+(end-start)+"ms.");
        return result;
    }
}
