package com.cloudm.framework.common.helper;

/**
 * @description:
 * @author: Courser
 * @date: 2017/3/17
 * @version: V1.0
 */
public class SmsHelper {
    //请求地址
    private static String URL = ConfigHelper.getSmsUrl();
    //TOP分配给应用的AppKey
    private static String APP_KEY = ConfigHelper.getSmsAppKey();
    //短信签名AppKey对应的secret值
    private static String SECRET = ConfigHelper.getSmsSecret();
    //短信类型，传入值请填写normal
    private static String SMS_TYPE = ConfigHelper.getSmsType();
    //阿里大于账户配置的短信签名
    private static String SMS_SIGN = ConfigHelper.getSmsSign();
    //阿里大于账户配置的短信模板ID
    private static String SMS_TEMPLATE_CODE = ConfigHelper.getSmsTemplateCode();
}
