package com.yufan.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.yufan.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 创建人: lirf
 * 创建时间:  2019/12/15 13:16
 * 功能介绍:  登录用户信息
 */
@Setter
@Getter
public class LoginUser {

    @JSONField(name = "user_id")
    private Integer userId;

    @JSONField(name = "nick_name")
    private String nickName;

    @JSONField(name = "user_mobile")
    private String userMobile;

    @JSONField(name = "member_id")
    private String memberId;

    @JSONField(name = "money")
    private BigDecimal money;

    @JSONField(name = "start_time")
    private String startTime;

    @JSONField(name = "end_time")
    private String endTime;

    @JSONField(name = "user_img")
    private String userImg;

    @JSONField(name = "sns_img")
    private String snsImg;

    @JSONField(name = "is_use_img")
    private String isUseImg;
}
