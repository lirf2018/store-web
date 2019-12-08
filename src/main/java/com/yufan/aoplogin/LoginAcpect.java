package com.yufan.aoplogin;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建人: lirf
 * 创建时间:  2019/12/8 19:39
 * 功能介绍: 定义登录拦截切面
 */
@Aspect
@Component
public class LoginAcpect {


    private Logger LOG = LoggerFactory.getLogger(LoginAcpect.class);

    /**
     * 定义切入点，切入点为com.yufan.controller下的所有函数
     */
    @Pointcut("execution(public  * com.yufan.controller..*.*(..))")
    public void loginAsp() {

    }

    @Before("loginAsp()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            LOG.error("------------------Before---------------------");
            String path=request.getContextPath();
            response.sendRedirect(path+"/other/otherLogin");
        } catch (Exception e) {
            LOG.error("----e---");
        }
    }




}
