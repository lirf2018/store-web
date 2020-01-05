package com.yufan.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.LoginUser;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import com.yufan.utils.HttpsRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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

        JSONObject result = CommonMethod.infoResult(data, Constants.INFO_MAIN);
        if (null != result) {
            JSONObject dataJson = result.getJSONObject("data");
            JSONArray activityList = dataJson.getJSONArray("activity_list");
            for (int i = 0; i < activityList.size(); i++) {
                modelAndView.addObject("activityImg" + i, activityList.getJSONObject(i).getString("activity_img"));
                modelAndView.addObject("activityLink" + i, activityList.getJSONObject(i).getString("activity_link"));
            }
            modelAndView.addObject("data", result.getJSONObject("data"));
        }
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping("searchPage")
    public ModelAndView searchPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        JSONObject data = new JSONObject();
        data.put("user_id", user.getUserId());
        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_HISTORY_LIST);
        if (null != result) {
            modelAndView.addObject("data", result.getJSONObject("data"));
        }
        modelAndView.setViewName("search-page");
        return modelAndView;
    }


    /**
     * 删除搜索历史结果
     *
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping("delUserHistory")
    public void delUserHistory(HttpServletRequest request, HttpServletResponse response, int id) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();

            LoginUser user = (LoginUser)request.getSession().getAttribute("user");
            JSONObject data = new JSONObject();
            data.put("user_id", user.getUserId());
            JSONObject result = CommonMethod.infoResult(data, Constants.DELETE_SEARCH_HISTORY);

            JSONObject pageData = new JSONObject();
            pageData.put("code", result.getInteger("resp_code"));
            if (result.getInteger("resp_code") == 1) {
                pageData.put("msg", "删除成功");
            } else {
                pageData.put("msg", "删除失败");
            }
            pageData.put("data", result.getJSONObject("data"));
            writer.println(pageData);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
