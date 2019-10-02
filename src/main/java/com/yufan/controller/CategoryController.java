package com.yufan.controller;

import com.alibaba.fastjson.JSONObject;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人: lirf
 * 创建时间:  2019/10/2 11:16
 * 功能介绍: 类目查询
 */
@Controller
@RequestMapping(value = "/category/")
public class CategoryController {

    @RequestMapping("categoryPage")
    public ModelAndView categoryPage() {
        ModelAndView modelAndView = new ModelAndView();

        JSONObject data = new JSONObject();

        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_LEVEL_CATEGORY_LIST);
        if (null != result&&result.getInteger("resp_code")==1) {
            modelAndView.addObject("data", result.getJSONObject("data"));
        }
        modelAndView.setViewName("category-list");
        return modelAndView;
    }
}
