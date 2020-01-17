package com.yufan.controller;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.LoginUser;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建人: lirf
 * 创建时间:  2020/1/17 23:04
 * 功能介绍:
 */
@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("t1")
    public ModelAndView searchPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");
        return modelAndView;
    }

}
