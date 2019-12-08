package com.yufan.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.UserAddr;
import com.yufan.utils.CommonMethod;
import com.yufan.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建人: lirf
 * 创建时间:  2019/10/5 21:55
 * 功能介绍:
 */
@Controller
@RequestMapping(value = "/center/")
public class UserCenterController {

    private Logger LOG = Logger.getLogger(UserCenterController.class);

    /**
     * 购物车列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("userCenter")
    public ModelAndView userCenter(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        JSONObject data = new JSONObject();
        data.put("user_id", 1);
        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_USER_CENTER);
        if (null != result && result.getInteger("resp_code") == 1) {
            modelAndView.addObject("data", result.getJSONObject("data"));
        }
        modelAndView.setViewName("user-center");
        return modelAndView;

    }

    /**
     * 用户地址列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("userAddr")
    public ModelAndView userAddr(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        JSONObject data = new JSONObject();
        data.put("user_id", 1);
        data.put("addr_type", 1);
        data.put("parent_code", "000000000000");

        JSONArray list = new JSONArray();//省

        JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_USER_ADDR);
        if (null != result && result.getInteger("resp_code") == 1) {
            modelAndView.addObject("data", result.getJSONObject("data"));
            //处理地址
            JSONArray array = result.getJSONObject("data").getJSONArray("country_addr_list");
            for (int i = 0; i < array.size(); i++) {
                JSONObject o = array.getJSONObject(i);
                String regionId = o.getString("region_id");
                String regionName = o.getString("region_name");
                String freight = o.getString("freight");
                String regionCode = o.getString("region_code");
                String word = regionId + "`" + regionName + "`" + freight + "`" + regionCode;
                Map<String, Object> map1 = new HashMap<>();
                map1.put("n", word);
                map1.put("c", new JSONArray());
                list.add(map1);
            }
            modelAndView.addObject("list", list);
        }
        modelAndView.setViewName("user-addr");
        return modelAndView;
    }


    /**
     * 查询地址列表
     *
     * @param request
     * @param response
     * @param parentCode
     */
    @RequestMapping("queryCountryAddr")
    public void queryCountryAddr(HttpServletRequest request, HttpServletResponse response, String parentCode, Integer regionLevel) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();

            JSONArray list = new JSONArray();//市、县

            JSONObject resultJson = new JSONObject();
            JSONObject data = new JSONObject();
            data.put("parent_code", StringUtils.isEmpty(parentCode) ? "000000000000" : parentCode);
            JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_COUNTRY_ADDR_LIST);
            if (result != null && result.getInteger("resp_code") == 1) {
                //处理地址
                JSONArray array = result.getJSONObject("data").getJSONArray("country_addr_list");
                for (int i = 0; i < array.size(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    String regionId = o.getString("region_id");
                    String regionName = o.getString("region_name");
                    String freight = o.getString("freight");
                    String regionCode = o.getString("region_code");
                    String word = regionId + "`" + regionName + "`" + freight + "`" + regionCode;
                    if (regionLevel == 1) {
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("n", word);
                        map1.put("c", new JSONArray());
                        list.add(map1);
                    } else if (regionLevel == 2) {
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("n", word);
                        map1.put("a", new JSONArray());
                        list.add(map1);
                    } else {
                        list.add(word);
                    }
                }
            }
            resultJson.put("data", list);
            writer.println(resultJson);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存用户地址
     */
    @RequestMapping("saveUserAddr")
    public void saveUserAddr(HttpServletRequest request, HttpServletResponse response, UserAddr userAddr) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            userAddr.setUserId(1);
            JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(userAddr));
            JSONObject result = CommonMethod.infoResult(data, Constants.ADD_USER_ADDR);


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
     * 设置默认
     */
    @RequestMapping("defaulAddr")
    public void defaulAddr(HttpServletRequest request, HttpServletResponse response, int id) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject data = new JSONObject();
            data.put("user_id", 1);
            data.put("id", id);
            JSONObject result = CommonMethod.infoResult(data, Constants.UPDATE_ADDR_DEFAULT);


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
     * 设置默认
     */
    @RequestMapping("deleteUserAddr")
    public void deleteUserAddr(HttpServletRequest request, HttpServletResponse response, int id) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject data = new JSONObject();
            data.put("user_id", 1);
            data.put("id", id);
            JSONObject result = CommonMethod.infoResult(data, Constants.DELETE_USER_ADDR);


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
     * 查询用户收货地址
     *
     * @param request
     * @param response
     */
    @RequestMapping("queryUserAddr")
    public void queryUserAddr(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject data = new JSONObject();
            data.put("user_id", 1);
            data.put("addr_type", 1);
            data.put("parent_code", "");

            JSONObject pageData = new JSONObject();
            pageData.put("code", 0);

            JSONObject result = CommonMethod.infoResult(data, Constants.QUERY_USER_ADDR);
            if (result != null && result.getInteger("resp_code") == 1) {
                pageData.put("code", 1);
                pageData.put("data", result.getJSONObject("data").getJSONArray("user_addr_list"));
            }
            writer.println(pageData);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务中心
     *
     * @return
     */
    @RequestMapping("toServicePage")
    public String toServicePage() {
        return "service-center";
    }

    /**
     * 绑定手机号码
     *
     * @return
     */
    @RequestMapping("toBangPhone")
    public String toBangPhone() {
        return "bang-phone";
    }

}
