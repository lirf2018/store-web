package com.yufan.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人: lirf
 * 创建时间:  2019/6/26 16:24
 * 功能介绍:  配置拦截器
 */

/**
 * 使用WebMvcConfigurer可以来扩展SpringMVC的功能
 * <p>
 * 在spring5.0之前可以继承WebMvcConfigurerAdapter此适配器进行配置，但spring5.0以后此适配器就被废弃（已被标注为@Deprecated），
 * 目前有两种解决方案，一种是直接实现WebMvcConfigurer，另一种是直接继承WebMvcConfigurationSupport，官方推荐第一种方案。
 */
@Configuration
public class MyInterceptorConf implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("------------------------->MyInterceptorConf");
//         addPathPatterns - 用于添加拦截规则
//         excludePathPatterns - 用户排除拦截

        List<String> patterns = new ArrayList<>();
        patterns.add( "/login/userLogin");
        patterns.add( "/login/phoneLogin");
        patterns.add( "/login/resetPasswd");
        patterns.add( "/login/sendPhoneCode");
        patterns.add( "/login/resetLoginPasswd");
        patterns.add( "/login/phoneCodeLogin");
        patterns.add( "/login/passwdLogin");
        patterns.add( "/css/**");
        patterns.add( "/js/**");
        patterns.add( "/img/**");

        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
