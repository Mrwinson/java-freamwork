package com.cloudm.framework.common.helper;


import com.cloudm.framework.common.consts.ConfigConstant;
import com.cloudm.framework.common.util.PropsUtil;
import com.cloudm.framework.common.util.StringUtil;

import java.util.Objects;
import java.util.Properties;

/**
 * @description: 属性文件辅助类
 * @author: Courser
 * @date:  2017/3/16
 * @version: V1.0
 */
public final class ConfigHelper {

    private static final Properties COFNIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE) ;

    /**
     * 获取 jpush app key
     * @return
     */
    public static   String  getJpushAppKey(){
        return PropsUtil.getString(COFNIG_PROPS,ConfigConstant.JPUSH_APP_KEY);
    }

    /**
     * 获取 jpush master secret
     * @return
     */
    public static String  getJpushMasterSecret(){
        return PropsUtil.getString(COFNIG_PROPS,ConfigConstant.JPUSH_MASTER_SECRET);
    }
    /**
     * 获取 jpush apnsProduction
     * @return
     */
    public static  Boolean  getJpushApnsProduction(){
        return PropsUtil.getBoolean(COFNIG_PROPS,ConfigConstant.JPUSH_APNS_PRODUCTION);
    }



}
