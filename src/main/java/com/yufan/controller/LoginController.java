package com.yufan.controller;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.LoginUser;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import com.yufan.utils.MD5;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 创建人: lirf
 * 创建时间:  2019/12/15 20:08
 * 功能介绍:  用户登录
 */
@Controller
@RequestMapping("/login/")
public class LoginController {

    private Logger LOG = Logger.getLogger(LoginController.class);


    /**
     * 账号密码登录
     *
     * @return
     */
    @RequestMapping("userLogin")
    public ModelAndView userLoginPage() {
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("login");
        return modelAndView;
    }


    /**
     * 验证码密码登录
     *
     * @return
     */
    @RequestMapping("phoneLogin")
    public ModelAndView phoneLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("validType", Constants.VALID_TYPE_6);
        modelAndView.setViewName("login2");
        return modelAndView;
    }


    /**
     * 账号注销
     *
     * @return
     */
    @RequestMapping("cancelAccount")
    public ModelAndView cancelAccountPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        LoginUser user = (LoginUser)request.getSession().getAttribute("user");
        modelAndView.addObject("phone", user.getUserMobile());
        modelAndView.addObject("validType", Constants.VALID_TYPE_7);
        modelAndView.setViewName("cancel-account");
        return modelAndView;
    }


    /**
     * 第三方账号绑定
     *
     * @return
     */
    @RequestMapping("bangRegister")
    public ModelAndView bangRegisterPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        LoginUser user = (LoginUser)request.getSession().getAttribute("user");
        modelAndView.addObject("phone", user.getUserMobile());
        modelAndView.setViewName("bang-account");
        return modelAndView;
    }

    /**
     * 密码重置
     *
     * @return
     */
    @RequestMapping("resetPasswd")
    public ModelAndView resetPasswdPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Object user = request.getSession().getAttribute("user");
        String phone = "";
        int flag = 0;
        if (null != user) {
            phone = ((LoginUser) user).getUserMobile();
            flag = 1;
        }
        modelAndView.addObject("phone", phone);
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("validType", Constants.VALID_TYPE_3);

        modelAndView.setViewName("reset-passwd");
        return modelAndView;
    }

    /**
     * 发送手机验证码
     * 验证码类型validType:1手机绑定2修改密码3重置密码4手机解绑5手机注册
     * 验证码说明validDesc
     */
    @RequestMapping("sendPhoneCode")
    public void sendPhoneCode(HttpServletRequest request, HttpServletResponse response, Integer validType, String validDesc, String phone) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject data = new JSONObject();
            data.put("valid_type", validType);
            data.put("valid_param", phone);//手机号码
            data.put("valid_desc", validDesc);
            JSONObject result = CommonMethod.infoResult(data, Constants.SEND_PHONE_CODE);
            writer.println(result);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 重置登录密码
     *
     * @param request
     * @param response
     * @param phoneCode
     * @param passwd
     * @param phone
     */
    @RequestMapping("resetLoginPasswd")
    public void resetLoginPasswd(HttpServletRequest request, HttpServletResponse response, String phoneCode, String passwd, String phone) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject data = new JSONObject();
            data.put("phone", phone);
            data.put("phone_code", phoneCode);
            String newPasswd = MD5.enCodeStandard((phone + "" + passwd));
            data.put("passwd", newPasswd);
            JSONObject result = CommonMethod.infoResult(data, Constants.RESET_PASSWD);
            writer.println(result);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 手机验证码登录
     */
    @RequestMapping("phoneCodeLogin")
    public void phoneCodeLogin(HttpServletRequest request, HttpServletResponse response, String phoneCode, String phone) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject data = new JSONObject();
            data.put("phone", phone);
            data.put("phone_code", phoneCode);
            JSONObject result = CommonMethod.infoResult(data, Constants.PHONE_CODE_LOGIN);
            if (null != result && result.getInteger("resp_code") == 1) {
                LOG.info("---登录成功--");
                LoginUser user = JSONObject.toJavaObject(result.getJSONObject("data"), LoginUser.class);
                request.getSession().setAttribute("user", user);
            }
            writer.println(result);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 账号密码登录
     *
     * @param request
     * @param response
     * @param passwd
     */
    @RequestMapping("passwdLogin")
    public void passwdLogin(HttpServletRequest request, HttpServletResponse response, String phone, String passwd) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject data = new JSONObject();
            data.put("login_name", phone);
            String passwdMd5 = MD5.enCodeStandard(phone + "" + passwd);
            data.put("passwd", passwdMd5);
            JSONObject result = CommonMethod.infoResult(data, Constants.PASSWD_LOGIN);
            if (null != result && result.getInteger("resp_code") == 1) {
                LOG.info("---登录成功--");
                LoginUser user = JSONObject.toJavaObject(result.getJSONObject("data"), LoginUser.class);
                request.getSession().setAttribute("user", user);
            }
            writer.println(result);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注销账号
     * @param request
     * @param response
     * @param phone
     * @param phoneCode
     */
    @RequestMapping("ajaxCancelAccount")
    public void ajaxCancelAccount(HttpServletRequest request, HttpServletResponse response, String phone, String phoneCode) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            LoginUser user = (LoginUser)request.getSession().getAttribute("user");
            JSONObject data = new JSONObject();
            data.put("phone", phone);
            data.put("user_id", user.getUserId());
            data.put("phone_code", phoneCode);
            JSONObject result = CommonMethod.infoResult(data, Constants.CANCEL_ACCOUNT);
            if (null != result && result.getInteger("resp_code") == 1) {
                request.getSession().invalidate();
            }
            writer.println(result);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出
     *
     * @param request
     * @param response
     */
    @GetMapping(value = "userExit")
    public void userExit(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/login/userLogin");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
