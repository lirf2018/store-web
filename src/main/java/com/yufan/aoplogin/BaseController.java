package com.yufan.aoplogin;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.ModalExclude;

/**
 * 创建人: lirf
 * 创建时间:  2019/12/8 19:37
 * 功能介绍:
 */
public abstract class BaseController {

    private Logger LOG = Logger.getLogger(BaseController.class);


    /**
     * 登录拦截跳转到绑定页面
     *
     * @return
     */
    public ModelAndView loginBangPhone() {
        ModelAndView modelAndView = new ModelAndView();

        LOG.info("--------loginBangPhone---------");

        modelAndView.setViewName("test");
        return modelAndView;
    }

    /**
     * 获取第三方信息
     *
     * @return
     */
    public ModelAndView loginBangOther() {
        ModelAndView modelAndView = new ModelAndView();

        LOG.info("--------loginBangOther---------");

        modelAndView.setViewName("test");
        return modelAndView;
    }


}
