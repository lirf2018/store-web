package com.yufan.controller;

import com.alibaba.fastjson.JSONObject;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import com.yufan.utils.HttpsRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人: lirf
 * 创建时间:  2019/8/23 17:38
 * 功能介绍:
 */
@Controller
@RequestMapping(value = "/index/")
public class MainController {

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("mainPage")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();

        JSONObject data = new JSONObject();

//        JSONObject result = CommonMethod.infoResult(data, Constants.INFO_MAIN);
//        if (null != result) {
//            modelAndView.addObject("data", result.getJSONObject("data"));
//        }
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping("searchPage")
    public ModelAndView searchPage() {
        ModelAndView modelAndView = new ModelAndView();

        JSONObject data = new JSONObject();
        data.put("user_id", 1);
        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_HISTORY_LIST);
        if (null != result) {
            modelAndView.addObject("data", result.getJSONObject("data"));
        }
        modelAndView.setViewName("search-page");
        return modelAndView;
    }

}
