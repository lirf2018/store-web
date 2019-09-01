package com.yufan.controller;

import com.alibaba.fastjson.JSONObject;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 创建人: lirf
 * 创建时间:  2019/8/31 22:07
 * 功能介绍:
 */
@Controller
@RequestMapping(value = "/xc/")
public class SecondGoodsController {


    /**
     * 闲菜页面
     *
     * @return
     */
    @RequestMapping("xcPage")
    public ModelAndView toXiancaiPage(HttpServletRequest request, HttpServletResponse response, String goodsName) {
        ModelAndView modelAndView = new ModelAndView();

        //JSONObject data = new JSONObject();
        //JSONObject result = CommonMethod.infoResult(data, Constants.XC_LIST);
        //if (null != result) {
        // modelAndView.addObject("data", result.getJSONObject("data"));
        //}
        JSONObject data = new JSONObject();
        data.put("curre_page", 1);
        data.put("goods_name", goodsName);
        modelAndView.addObject("goodsName", goodsName);
        modelAndView.setViewName("xc-list");
        return modelAndView;

    }

    /**
     * 加载闲菜分页数据
     *
     * @param request
     * @param response
     * @param goodsName
     */
    @RequestMapping("xcPageData")
    public void loadXcPageData(HttpServletRequest request, HttpServletResponse response, String goodsName, Integer currePage) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();

            JSONObject out = new JSONObject();
            out.put("code", 23);
            out.put("msg", CommonMethod.packagMsg("23"));

            JSONObject data = new JSONObject();
            data.put("curre_page", currePage);
            data.put("goods_name", goodsName);
            JSONObject result = CommonMethod.infoResult(data, Constants.XC_LIST);
            if (null != result) {
                out.put("code", 1);
                out.put("data", result.getJSONObject("data"));
            }
            writer.println(out);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 闲菜详情页面
     *
     * @return
     */
    @RequestMapping("xcDetailPage")
    public ModelAndView toXiancaiDetailPage(HttpServletRequest request, HttpServletResponse response, Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        JSONObject data = new JSONObject();
        data.put("id", id);
        JSONObject result = CommonMethod.infoResult(data, Constants.XC_DETAIL);
        if (null != result) {
            JSONObject resultData = result.getJSONObject("data");
            modelAndView.addObject("goods_id", resultData.getInteger("goods_id"));
            modelAndView.addObject("goods_name", resultData.getString("goods_name"));
            modelAndView.addObject("goods_img", resultData.getString("goods_img"));
            modelAndView.addObject("true_price", resultData.getBigDecimal("true_price"));
            modelAndView.addObject("now_price", resultData.getBigDecimal("now_price"));
            modelAndView.addObject("new_info", resultData.getInteger("new_info"));
            modelAndView.addObject("is_post", resultData.getInteger("is_post"));
            modelAndView.addObject("about_price", resultData.getInteger("about_price"));
            modelAndView.addObject("goods_info", resultData.getString("goods_info"));
            modelAndView.addObject("goods_shop_code", resultData.getString("goods_shop_code"));
        }
        modelAndView.setViewName("xc-detail");
        return modelAndView;

    }
}
