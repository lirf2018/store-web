package com.yufan.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ShopCartBean;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            shopCartBean.setUserId(1);

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

    /**
     * 订单提交创建
     */
    @RequestMapping("toSubmitPage")
    public ModelAndView toSubmitPage(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        //店铺信息
        String shopName = request.getParameter("shopName");
        /*//商品通用信息
        String goodsIds = request.getParameter("goodsIds");
        String[] goodsIdsArray = goodsIds.split("`");
        String goodsIdNames = request.getParameter("goodsIdNames");
        String[] goodsIdNamesArray = goodsIdNames.split("`");
        String goodsImgs = request.getParameter("goodsImgs");
        String[] goodsImgsArray = goodsImgs.split("`");
        String goodsCounts = request.getParameter("goodsCounts");
        String[] goodsCountsArray = goodsCounts.split("`");
        String goodsNowPrices = request.getParameter("goodsNowPrices");
        String[] goodsNowPricesArray = goodsNowPrices.split("`");//现价
        String goodsTurePrices = request.getParameter("goodsTurePrices");
        String[] goodsTurePricesArray = goodsTurePrices.split("`");//原价
        //sku商品信息
        String skuIds = request.getParameter("skuIds");
        String[] skuIdsArray = {};//商品skuIds
        if (StringUtils.isNotEmpty(skuIds)) {
            skuIdsArray = skuIds.split("`");
        }
        String skuNames = request.getParameter("skuNames");
        String[] skuNamesArray = {};//商品sku规格名称
        if (StringUtils.isNotEmpty(skuNames)) {
            skuNamesArray = skuNames.split("`");
        }
        //购物车Ids
        String shopCartIds = request.getParameter("shopCartIds");
        String[] shopCartIdsArray = {};
        if (StringUtils.isNotEmpty(shopCartIds)) {
            shopCartIdsArray = shopCartIds.split("`");
        }*/
        //商品通用信息
        String[] goodsIdsArray = request.getParameterValues("goodsIds");
        String[] goodsIdNamesArray = request.getParameterValues("goodsIdNames");
        String[] goodsImgsArray = request.getParameterValues("goodsImgs");
        String[] goodsCountsArray = request.getParameterValues("goodsCounts");
        String[] goodsNowPricesArray = request.getParameterValues("goodsNowPrices");//现价
        String[] goodsTurePricesArray = request.getParameterValues("goodsTurePrices");//原价
        //sku商品信息
        String[] skuIdsArray = request.getParameterValues("skuIds");//商品skuIds
        String[] skuSpecNamesArray = request.getParameterValues("skuSpecNames");//商品sku规格名称
        //购物车Ids
        String[] shopCartIdsArray = request.getParameterValues("shopCartIds");

        //选中的购物车
        String shopCartIdsCheck = request.getParameter("shopCartIdsCheck");
        Map<String, String> shopCartIdsCheckMap = new HashMap<>();//选中的购物车
        if (StringUtils.isNotEmpty(shopCartIdsCheck)) {
            String[] shopCartIdsCheckStrArray = shopCartIdsCheck.split("`");
            for (int i = 0; i < shopCartIdsCheckStrArray.length; i++) {
                if(StringUtils.isEmpty(shopCartIdsCheckStrArray[i])){
                    continue;
                }
                shopCartIdsCheckMap.put(shopCartIdsCheckStrArray[i], shopCartIdsCheckStrArray[i]);
            }
        }

        //抢购商品信息
        Integer timeGoodsId = Integer.parseInt(request.getParameter("timeGoodsId"));//抢购商品标识
        //邮费
        BigDecimal freight = new BigDecimal("0.00");

        //查询用户收货地址
        String userAddrId = "";
        String userName = "";
        String userPhone = "";
        String addrName = "";

        JSONObject data = new JSONObject();
        data.put("user_id", 1);
        data.put("addr_type", 1);
        data.put("parent_code", "000000000000");
        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_USER_ADDR);
        JSONArray list = new JSONArray();//省列表
        if (null != result && result.getInteger("resp_code") == 1) {
            modelAndView.addObject("data", result.getJSONObject("data"));
            //用户地址(默认地址)is_default
            JSONArray userAddrList = result.getJSONObject("data").getJSONArray("user_addr_list");
            if (null != userAddrList && userAddrList.size() > 0) {
                for (int i = 0; i < userAddrList.size(); i++) {
                    JSONObject obj = userAddrList.getJSONObject(i);
                    int isDefault = obj.getInteger("is_default");
                    if (isDefault == 1) {
                        userAddrId = obj.getString("id");
                        userName = obj.getString("user_name");
                        userPhone = obj.getString("user_phone");
                        addrName = obj.getString("addr_name");
                        freight = obj.getBigDecimal("freight");
                        break;
                    }
                }
            }

            //处理地址
            JSONArray array = result.getJSONObject("data").getJSONArray("country_addr_list");
            for (int i = 0; i < array.size(); i++) {
                JSONObject o = array.getJSONObject(i);
                String regionId = o.getString("region_id");
                String regionName = o.getString("region_name");
                String freight_ = o.getString("freight");
                String regionCode = o.getString("region_code");
                String word = regionId + "`" + regionName + "`" + freight_ + "`" + regionCode;
                Map<String, Object> map1 = new HashMap<>();
                map1.put("n", word);
                map1.put("c", new JSONArray());
                list.add(map1);
            }
            modelAndView.addObject("list", list);
        }

        //商品总价
        BigDecimal goodsPriceAll = new BigDecimal("0.00");

        //处理商品
        List<Map<String, Object>> goodsList = new ArrayList<>();
        for (int i = 0; i < goodsIdsArray.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("goodsId", goodsIdsArray[i]);
            map.put("goodsName", goodsIdNamesArray[i]);
            map.put("goodsImg", goodsImgsArray[i]);
            BigDecimal goodsCount = new BigDecimal(goodsCountsArray[i]);
            BigDecimal goodsNowPrice = new BigDecimal(goodsNowPricesArray[i]);
            BigDecimal goodsPriceCount = goodsNowPrice.multiply(goodsCount);
            //sku商品信息
            if (null != skuIdsArray && skuIdsArray.length > 0) {
                map.put("skuId", skuIdsArray[i]);
                map.put("skuSpecName", StringUtils.isEmpty(skuSpecNamesArray[i])?"":"规格:"+skuSpecNamesArray[i]);
            } else {
                map.put("skuId", "0");
                map.put("skuName", "");
            }
            //购物车Ids
            if (StringUtils.isNotEmpty(shopCartIdsCheck)) {
                //来自购物车   判断购物车是否是选中的
                if (shopCartIdsCheckMap.get(shopCartIdsArray[i]) == null) {
                    continue;
                }
                map.put("shopCartId", shopCartIdsArray[i]);
            } else {
                map.put("shopCartId", 0);
            }
            goodsPriceAll = goodsPriceAll.add(goodsPriceCount);
            map.put("goodsNowPrice", goodsNowPrice.toString());
            map.put("goodsTruePrice", goodsTurePricesArray[i]);
            map.put("goodsCount", goodsCount);
            goodsList.add(map);
        }

        //设置订单提交页面参数
        modelAndView.addObject("shopName", shopName);
        modelAndView.addObject("goodsList", goodsList);
        modelAndView.addObject("goodsPriceAll", goodsPriceAll);
        modelAndView.addObject("goodsList", goodsList);
        modelAndView.addObject("timeGoodsId", timeGoodsId);
        //用户收货信息
        modelAndView.addObject("userAddrId",userAddrId);
        modelAndView.addObject("userName",userName);
        modelAndView.addObject("userPhone",userPhone);
        modelAndView.addObject("addrName",addrName);
        modelAndView.addObject("freight", freight.setScale(2, BigDecimal.ROUND_HALF_UP));
        //
        modelAndView.setViewName("order-submit");
        return modelAndView;
    }


}
