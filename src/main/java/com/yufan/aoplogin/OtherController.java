package com.yufan.aoplogin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人: lirf
 * 创建时间:  2019/12/8 20:32
 * 功能介绍:
 */
@Controller
@RequestMapping("/other/")
public class OtherController {

    private Logger LOG = Logger.getLogger(OtherController.class);


    @RequestMapping("otherLogin")
    public ModelAndView otherLogin() {
        ModelAndView modelAndView = new ModelAndView();

        LOG.info("--------otherLogin---------");

        modelAndView.setViewName("test");
        return modelAndView;
    }

}
