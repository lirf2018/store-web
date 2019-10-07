package com.yufan.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * 创建人: lirf
 * 创建时间:  2019/10/7 11:07
 * 功能介绍: 用户地址
 */
@Getter
@Setter
public class UserAddr {

    @JSONField(name = "id")
    private Integer id;

    @JSONField(name = "user_id")
    private Integer userId;

    @JSONField(name = "area_Ids")
    private String areaIds;//所在地区组合标识(或者平台地址标识)

    @JSONField(name = "area_names")
    private String areaNames;//所在地区组合名称

    @JSONField(name = "addr_detail")
    private String addrDetail;//详细地址

    @JSONField(name = "addr_type")
    private Integer addrType = 1;//地址类型1全国地址2平台配送或者自己取地址

    @JSONField(name = "user_phone")
    private String userPhone;

    @JSONField(name = "user_name")
    private String userName;


}
