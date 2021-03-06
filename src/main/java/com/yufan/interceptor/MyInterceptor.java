package com.yufan.interceptor;

import com.yufan.bean.LoginUser;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建人: lirf
 * 创建时间:  2019/6/26 16:36
 * 功能介绍:  自定义拦截器
 */
@Configuration
public class MyInterceptor implements HandlerInterceptor {

    private Logger LOG = Logger.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getSession().setAttribute("version", "1.0.0");
        LOG.info("preHandle:请求前调用url:" + request.getRequestURL());
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        String uri = request.getRequestURI();
        LOG.info("preHandle:请求前调用uri:" + uri);
        if (user == null && "/store-web/".equals(uri)) {
            response.sendRedirect(request.getContextPath() + "/login/userLogin");
            return false;
        }
        if (user == null) {
            LOG.info("------未登录,拦截-----");
            response.sendRedirect(request.getContextPath() + "/login/userLogin");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        LOG.info("postHandle:请求后调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        LOG.info("afterCompletion:请求调用完成后回调方法，即在视图渲染完成后回调");
    }
}
