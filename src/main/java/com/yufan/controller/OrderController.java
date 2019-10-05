package com.yufan.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ShopCartBean;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 创建人: lirf
 * 创建时间:  2019/10/4 12:54
 * 功能介绍:
 */
@Controller
@RequestMapping(value = "/order/")
public class OrderController {
    private Logger LOG = Logger.getLogger(OrderController.class);


    /**
     * 购物车列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("shopCartList")
    public ModelAndView shopCartList(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        JSONObject data = new JSONObject();
        data.put("user_id", 1);
        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_ORDER_CART);
        if (null != result && result.getInteger("resp_code") == 1) {
            //暂时不支持多个店铺
            JSONArray cartList = result.getJSONObject("data").getJSONArray("cart_list");
            if (cartList.size() == 1) {

                modelAndView.addObject("data", result.getJSONObject("data"));
            }
            if (cartList.size() > 0) {
                LOG.info("=======暂时只支持一个店铺========");
            }
        }

        modelAndView.setViewName("shop-cart");
        return modelAndView;

    }


    /**
     * 增加购物车
     *
     * @param request
     * @param response
     * @param shopCartBean
     */
    @RequestMapping("addShopCart")
    public void loadGoodsList(HttpServletRequest request, HttpServletResponse response, ShopCartBean shopCartBean) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(shopCartBean));
            JSONObject result = CommonMethod.infoResult(data, Constants.ADD_ORDER_CART);


            JSONObject pageData = new JSONObject();
            pageData.put("code", result.getInteger("resp_code"));
            if (result.getInteger("resp_code") == 1) {
                pageData.put("msg", "添加成功");
            } else {
                pageData.put("msg", "添加失败");
            }

            pageData.put("data", result.getJSONObject("data"));

            writer.println(pageData);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新购物车
     *
     * @param request
     * @param response
     */
    @RequestMapping("updateShopCart")
    public void updateShopCart(HttpServletRequest request, HttpServletResponse response, Integer cartId, Integer count) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject data = new JSONObject();
            data.put("user_id", 1);
            data.put("cart_id", cartId);
            data.put("goods_count", count);

            JSONObject result = CommonMethod.infoResult(data, Constants.ADD_ORDER_CART);

            JSONObject pageData = new JSONObject();
            pageData.put("code", result.getInteger("resp_code"));
            if (result.getInteger("resp_code") == 1) {
                pageData.put("msg", "添加成功");
            } else {
                pageData.put("msg", "添加失败");
            }

            pageData.put("data", result.getJSONObject("data"));

            writer.println(pageData);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新购物车
     *
     * @param request
     * @param response
     */
    @RequestMapping("deleteShopCart")
    public void deleteShopCart(HttpServletRequest request, HttpServletResponse response, String cartIds) {
        PrintWriter writer = null;
        try {

            cartIds = cartIds.substring(0, cartIds.length() - 1);
            writer = response.getWriter();
            JSONObject data = new JSONObject();
            data.put("user_id", 1);
            data.put("cart_ids", cartIds);

            JSONObject result = CommonMethod.infoResult(data, Constants.DELETE_ORDER_CART);

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
