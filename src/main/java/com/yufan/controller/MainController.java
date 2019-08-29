package com.yufan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人: lirf
 * 创建时间:  2019/8/23 17:38
 * 功能介绍:
 */
@Controller
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping("mainPage")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("main");
        return modelAndView;
    }

}
