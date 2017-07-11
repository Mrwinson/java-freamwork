package com.cloudm.framework.common.helper;



import java.util.Map;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 极光推送工具类
 * @author: Courser
 * @date: 2017/3/16
 * @version: V1.0
 */
@Slf4j
public class JPushHelper {
    private static final String appKey = ConfigHelper.getJpushAppKey();
    private static final String masterSecret = ConfigHelper.getJpushMasterSecret();
    private static final boolean apnsProduction = ConfigHelper.getJpushApnsProduction();

    /**
     * 按别名发送到单个用户
     * @param map 参数(type属性必须传入，如：map.put("type","***");)
     * @param alias 别名（即登录的ID）
     * @param msg 主体消息
     * @param title 标题
     * @param time
     * @return 0:不成功 1:android成功,IOS不成功 2:IOS成功,android不成功 3.都成功
     */
    public static long defaultPushByAlias(long alias, String msg, String title, int time,Map<String, String> map) throws Exception{
        long l = 0;
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
PushPayload pushPayload =null ;
        try {        //android 推送
         pushPayload = getPushPayload(Platform.android(),Notification.android(msg, title, map),alias, time);
        PushResult result = jpushClient.sendPush(pushPayload);
            if(null!=result && result.isResultOK()==true){//推送成功，返回true，l+1
                if(log.isInfoEnabled()){
                    log.info("result android={}" , result.isResultOK());
                }
                l += 1;
            }
        }catch (Exception e){
            log.error("as JPushHepler push msg is failed !params is ==>{},exception is ==>{}",pushPayload,e);
        }




        PushPayload pushPayloadIOS =null ;
        try {


            //ios 推送
            Notification iosNotification = Notification.newBuilder().addPlatformNotification(
                    IosNotification.newBuilder()
                            .setSound("default")
                            .setAlert(msg)
                            .addExtras(map).build())
                    .build();

         pushPayloadIOS = getPushPayload(Platform.ios(),iosNotification,alias, time);
        PushResult resultIOS = jpushClient.sendPush(pushPayloadIOS);
            if(null!=resultIOS && resultIOS.isResultOK()==true){//推送成功，返回true，l+2
                if(log.isInfoEnabled()){
                    log.info("result ios={}" , resultIOS.isResultOK());
                }
                l += 2;
            }
        }catch (Exception e){
            log.error("ios JPushHepler push msg is failed !params is ==>{},exception is ==>{}",pushPayloadIOS,e);
        }
        return l;

    }


    /**
     * 构造私有PushPayLoad
     * @param platform 平台
     * @param notification 通知
     * @param alias 别名
     * @param time 持续时间
     * @return
     */
    private static PushPayload getPushPayload(Platform platform,Notification notification,long alias, int time) {
        return PushPayload
                        .newBuilder()
                        .setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build())
                        .setOptions(Options.newBuilder().setBigPushDuration(time).build())
                        .setPlatform(platform)
                        .setAudience(Audience.alias(String.valueOf(alias)))
                        .setNotification(notification)
                        .build();
    }
}
