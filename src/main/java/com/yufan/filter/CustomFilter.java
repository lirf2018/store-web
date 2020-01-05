package com.yufan.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建人: lirf
 * 创建时间:  2019/6/26 16:44
 * 功能介绍:  过滤器
 */
@WebFilter(filterName = "customFilter", urlPatterns = {"/*"})
public class CustomFilter extends OncePerRequestFilter {// Filter {

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("-----------------------过滤器");
//        filterChain.doFilter(servletRequest, servletResponse);
//    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //System.out.println("-----------------------过滤器");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
