package com.yufan.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 创建人: lirf
 * 创建时间:  2019/8/27 10:14
 * 功能介绍:
 */
public class CommonMethod {

    /**
     * Unicode 转码
     *
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {

        char aChar;

        int len = theString.length();

        StringBuffer outBuffer = new StringBuffer(len);

        for (int x = 0; x < len; ) {

            aChar = theString.charAt(x++);

            if (aChar == '\\') {

                aChar = theString.charAt(x++);

                if (aChar == 'u') {

                    // Read the xxxx

                    int value = 0;

                    for (int i = 0; i < 4; i++) {

                        aChar = theString.charAt(x++);

                        switch (aChar) {

                            case '0':

                            case '1':

                            case '2':

                            case '3':

                            case '4':

                            case '5':

                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';

                    else if (aChar == 'n')

                        aChar = '\n';

                    else if (aChar == 'f')

                        aChar = '\f';

                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }


    /**
     * 处理结果参数
     *
     * @param code
     * @return
     */
    public static JSONObject packagMsg(String code) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", OutCode.getMsg(code));
        return json;
    }


    /**
     * 生成订单号，最长支持20位
     */
    public static synchronized String randomStr(String addWord) {
        String time = DatetimeUtil.getNow("yyyyMMddHHmmssSSS");
        Random random = new Random();
        int r = random.nextInt(899) + 100;//随机三位数
        return new StringBuffer().append(addWord).append(time).append(r).toString();
    }

    /**
     * 调用接口
     *
     * @return
     */
    public static JSONObject infoResult(JSONObject data, String reqType) {
        try {
            JSONObject out = new JSONObject();
            out.put("req_type", reqType);
            String timestamp = String.valueOf(System.currentTimeMillis()/1000);
            String sign = VerifySign.getSign(data, Constants.SID, Constants.SECRET_KEY, timestamp);
            out.put("sid", Constants.SID);
            out.put("timestamp", timestamp);
            out.put("sign", sign);
            out.put("data", data);
            String result = RequestMethod.httpPost(Constants.INFO_URL, out.toString());
            if (StringUtils.isNotEmpty(result)) {
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
