package com.yufan.controller;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.GoodsCondition;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 创建人: lirf
 * 创建时间:  2019/10/2 12:08
 * 功能介绍:
 */
@Controller
@RequestMapping(value = "/goods/")
public class GoodsController {

    @RequestMapping("goodsList")
    public ModelAndView goodsList(HttpServletRequest request, HttpServletResponse response, GoodsCondition goodsCondition) {
        ModelAndView modelAndView = new ModelAndView();

        if (goodsCondition.getCurrePage() == null) {
            goodsCondition.setCurrePage(1);
        }

        JSONObject data = new JSONObject();
        data.put("curre_page", goodsCondition.getCurrePage());
        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_QUERY_GOODS_LIST);
        if (null != result && result.getInteger("resp_code") == 1) {
            modelAndView.addObject("data", result.getJSONObject("data"));
        }
        modelAndView.setViewName("goods-list");
        return modelAndView;
    }

    /**
     * 查询商品列表
     *
     * @param request
     * @param response
     * @param goodsCondition
     */
    @RequestMapping("loadGoodsList")
    public void loadGoodsList(HttpServletRequest request, HttpServletResponse response, GoodsCondition goodsCondition) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject pageData = new JSONObject();
            pageData.put("code", 0);

            if (goodsCondition.getCurrePage() == null) {
                goodsCondition.setCurrePage(1);
            }
            JSONObject data = new JSONObject();
            data.put("curre_page", goodsCondition.getCurrePage());
            JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_QUERY_GOODS_LIST);
            if (null != result && result.getInteger("resp_code") == 1) {
                pageData.put("code", 1);
                pageData.put("data", result.getJSONObject("data"));
            }
            writer.println(pageData);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询商品详情
     *
     * @param request
     * @param response
     * @param goodsId
     * @return
     */
    @RequestMapping("goodsDetail")
    public ModelAndView goodsDetail(HttpServletRequest request, HttpServletResponse response, Integer goodsId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");
        JSONObject data = new JSONObject();
        data.put("goods_id", goodsId);
        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_QUERY_GOODS_DETAIL);
        if (null != result && result.getInteger("resp_code") == 1) {
            JSONObject resultData = result.getJSONObject("data");
            modelAndView.addObject("data", resultData);
            Integer isSingle = resultData.getInteger("is_single");
            if(isSingle == 0){
                //sku商品
                modelAndView.setViewName("goods-detail-sku");
            }else{
                modelAndView.setViewName("goods-detail");
            }
        }

        return modelAndView;
    }
}
