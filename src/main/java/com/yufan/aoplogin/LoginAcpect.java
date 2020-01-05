package com.yufan.aoplogin;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.expression.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建人: lirf
 * 创建时间:  2019/12/8 19:39
 * 功能介绍: 定义登录拦截切面
 */
/*@Aspect
@Component*/
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
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

            ////获取目标方法的参数信息
            Object[] obj = joinPoint.getArgs();
            for (int i = 0; i < obj.length; i++) {
                LOG.info("----------------------------------" + obj[i]);
            }

            //获取请求参数
            Enumeration<String> enumeration = request.getParameterNames();
            Map<String, String> parameterMap = new HashMap<>();
            while (enumeration.hasMoreElements()) {
                String parameter = enumeration.nextElement();
                parameterMap.put(parameter, request.getParameter(parameter));
            }
            String str = JSON.toJSONString(parameterMap);


            LOG.error("------------------Before---------------------" + str);
            String path = request.getContextPath();
            //response.sendRedirect(path+"/other/otherLogin");
        } catch (Exception e) {
            LOG.error("----e---");
        }
    }

    @After("loginAsp()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();








        } catch (Exception e) {
            LOG.error("----e---");
        }
    }


}
