package com.yufan.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 创建人: lirf
 * 创建时间:  2019/10/4 12:58
 * 功能介绍:
 */
@Getter
@Setter
public class ShopCartBean {


    @JSONField(name = "goods_id")
    private Integer goodsId;

    @JSONField(name = "user_id")
    private Integer userId;

    @JSONField(name = "goods_count")
    private Integer goodsCount;

    @JSONField(name = "goods_spec")
    private String goodsSpec;

}
