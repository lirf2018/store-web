package com.yufan.interceptor;

import com.yufan.pojo.TbAdmin;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 创建人: lirf
 * 创建时间:  2019/6/26 16:36
 * 功能介绍:  自定义拦截器
 */
//@Configuration
public class MyInterceptor implements HandlerInterceptor {
    private Logger LOG = Logger.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("preHandle:请求前调用" + request.getRequestURL());
        TbAdmin user = (TbAdmin) request.getSession().getAttribute("user");
        if (request.getRequestURL().indexOf("sweetalert") > -1) {
//            LOG.info("不拦截sweetalert");
            return true;
        }
        if (user == null) {
            LOG.info("未登录,拦截");
            PrintWriter pw = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            pw.print("<script language=javascript>alert('会话结束');</script>");
            pw.print("<script language=javascript>this.parent.parent.location = '" + request.getContextPath() + "/login/userLoginPage'</script>");
//            response.sendRedirect(request.getContextPath() + "/login/userLoginPage");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle:请求后调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("afterCompletion:请求调用完成后回调方法，即在视图渲染完成后回调");
    }
}
