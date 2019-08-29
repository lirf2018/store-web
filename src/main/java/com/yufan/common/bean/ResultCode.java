package com.yufan.common.bean;

public enum ResultCode {

    OK(1, "成功"),
    FAIL(-1, "失败,网络异常"),
    INFO_ERROR(1000, "接口不存在"),
    SYS_ERROR(1001, "系统错误，请稍后重试"),
    PARAM_ERROR(1002, "参数格式不正确"),
    PARAM_FORMAT_ERROR(1003, "参数解析失败"),
    ACCESS_TOKEN_INVALID(1004, "令牌无效"),
    INTERFACE_CALL_FAIL(1005, "接口调用失败"),
    OUT_OF_TIME(1006, "接口调用超时"),
    ERROR_SIGN(1007, "签名不正确"),
    ERROR_USER_BANGWEIXIN(1008, "绑定微信才能使用短信服务"),
    ERROR_USER_ISBANGWEIXIN(1009, "微信已被绑定"),
    ERROR_SMS(10010, "短信发送上限"),
    NEED_PARAM_ERROR(10011, "缺少必要参数"),
    STORE_EMPTY(10012, "商品库存不足"),
    FAIL_GET_TICKET(10013, "领取失败,请查看卡券领取限制规则"),
    FAIL_REGISTER(10014, "注册失败"),
    CODE_NONEFFECTIVE(10015, "验证码无效"),
    FAIL_USER_EXIST(10016, "登录名称已存在"),
    FAIL_BOUND_PHONE(10017, "该号码已被注册,请更换其它号码重试"),
    GOODS_NOT_EXIST(10018, "商品不存在"),
    FULL_ORDER_CARD(10019, "购物车已满"),
    FAIL_ADD_CARD_LIMIT_GOODS(10020, "操作失败,请查看商品限购规则"),
    FAIL_USER_INVALIDATE(10021, "用户无效"),
    FAIL_USER_LOCK(10022, "用户已锁定"),
    FAIL_USER_NEED_VERIFY(10023, "用户待验证"),
    LOGIN_NAME_CANNOT_NUM(10024, "登录名称不能是纯数字"),
    LOGIN_PHONE_NEED_VERIFY(10025, "登录手机待验证"),
    FAIL_NOTBANG_WEIXIN(10026, "账号未绑定微信"),
    FAIL_ADD_ORDERCARD(10027, "增加到购物车失败"),
    GOODSINFO_NOT_EXIST(10028, "商品数据有误"),
    GOODSINFO_MAX_OUT(10029, "商品种类数量超过上限"),
    ORDWE_PRICE_ERROR(10030, "订单价格计算有误"),
    ORDWE_COUNT_ERROR(10031, "订单数量计算有误"),
    DISCOUNT_ERROR(10032, "优惠全券无效"),
    ORDER_ALONE_ERROR(10033, "符合独立订单规则"),
    ORDER_LIMIT_ERROR(10034, "不符合限购规则,或者超出限购数,请认真查看限购规则后,重新下单"),
    FAIL_GOODS_INVALIDATE(10035, "商品无效"),
    GOODS_STORE_NOENOUGH(10036, "库存不足"),
    SMS_SEND_FAIL(10037, "短信发送失败"),
    OLD_PASSWD_EROOR(10038, "旧密码有误"),
    PHONE_HAS_USED(10039, "手机已经被使用"),
    PASSWD_ERROR(10040, "密码或账号不正确"),
    BIND_ERROR(10041, "未绑定"),
    NET_ERROR(10042, "网络异常,请稍后重试"),
    GOODS_OUTTIME_ERROR(10043, "部分商品已失效"),
    GOODS_PEISONG_ERROR(10044, "商品配送方式不一致"),
    ORDER_NOT_EXIST(10045, "订单不存在"),
    ERROR_WORD_QUESTION(10046, "密保问题有误"),
    ERROR_WORD_QUESTION_(10047, "密保问题不能相同"),
    BUSINESS_NOT_ERROR(10048, "验证码类型未开发"),
    ADDR_NOT_EXIST(10049, "收货地址无效"),
    GOODS_NOT_POST(10050, "商品不支持邮寄"),
    ORDER_NOT_POST(10051, "部分商品取货方式不支持 "),
    GOODS_CHECK_NOT_POST(10052, "商品取货方式不支持"),
    USER_ADDR_FULL(10053, "最多能保存10个有效收货地址"),
    GOODS_GETWAY_DIFF(10054, "商品取货方式不一致"),
    USER_PHONE_NOTUSE(10055, "手机未被绑定"),
    NOT_BOUND_WEIXIN(10056, "账号未绑定微信,不允许解绑"),
    NOT_BOUND_PHONE(10056, "账号未绑定手机号,不允许解绑"),
    PART_GOODS_STORE_NOENOUGH(10057, "部分商品库存不足"),
    QUERY_NOT_FIND(10058, "查询不存在");


    private int resp_code;
    private String resp_desc;

    private ResultCode(int resp_code, String resp_desc) {
        this.resp_code = resp_code;
        this.resp_desc = resp_desc;
    }

    /**
     * 获取编码
     *
     * @return
     */
    public int getResp_code() {
        return resp_code;
    }

    /**
     * 获取描述值
     *
     * @return
     */
    public String getResp_desc() {
        return resp_desc;
    }

    public static String getRespDesc(int resp_code) {
        String dt = "";
        for (ResultCode ac : ResultCode.values()) {
            if (ac.resp_code == resp_code) {
                dt = ac.getResp_desc();
                break;
            }
        }
        return dt;
    }


}
