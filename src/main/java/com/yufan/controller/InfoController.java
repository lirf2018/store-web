package com.yufan.controller;

import com.alibaba.fastjson.JSONObject;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人: lirf
 * 创建时间:  2020/1/18 14:48
 * 功能介绍: 资讯管理
 */
@Controller
@RequestMapping("info")
public class InfoController {

    private Logger LOG = Logger.getLogger(InfoController.class);


    /**
     * 查询资讯列表
     *
     * @return
     */
    @RequestMapping("infoList")
    public ModelAndView infoPage() {
        ModelAndView modelAndView = new ModelAndView();

        JSONObject data = new JSONObject();

        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_INFO_LIST);
        if (null != result && result.getInteger("resp_code") == 1) {
            modelAndView.addObject("data", result.getJSONObject("data"));
        }

        modelAndView.setViewName("info-list");
        return modelAndView;
    }

    /**
     * 查询资讯详情
     *
     * @return
     */
    @RequestMapping("infoDetail")
    public ModelAndView infoDetailPage(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        JSONObject data = new JSONObject();
        data.put("info_id", id);
        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_INFO_DETAIL);
        if (null != result && result.getInteger("resp_code") == 1) {
            modelAndView.addObject("data", result.getJSONObject("data"));
        }

        modelAndView.setViewName("info-detail");
        return modelAndView;
    }

}
