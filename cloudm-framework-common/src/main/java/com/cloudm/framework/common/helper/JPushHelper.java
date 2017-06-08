package com.cloudm.framework.common.helper;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.cloudm.framework.common.enums.BaseErrorEnum;
import com.cloudm.framework.common.ex.BusinessCheckFailException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Courser
 * @date: 2017/3/16
 * @version: V1.0
 */
@Slf4j
public class JPushHelper {
    private static final String appKey = ConfigHelper.getJpushAppKey();
    private static final String masterSecret = ConfigHelper.getJpushMasterSecret();
    private static final boolean apnsProduction = ConfigHelper.getJpushApnsProduction();
//
//    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
//
//        Map<String, String> ex = new HashMap<String, String>();
//
//        ex.put("diyigecanshu", "1");
//        ex.put("2", "2");
//        return PushPayload.newBuilder().setPlatform(Platform.android())
//                .setAudience(Audience.alias("31"))
//                // .setNotification(Notification.android(ALERT, TITLE, ex))
//                .build();
//    }

//    public static PushPayload buildPushObject_android_and_ios(String alias,
//                                                              String message, String page) {
//
//        Map<String, String> ext = new HashMap<String, String>();
//
//        ext.put("pageType", page);
//        // ex.put("2", "2");
//        return PushPayload
//                .newBuilder()
//                .setPlatform(Platform.android_ios())
//                .setAudience(Audience.alias(alias))
//                .setNotification(
//                        Notification
//                                .newBuilder()
//                                .setAlert(message)
//                                .addPlatformNotification(
//                                        AndroidNotification.newBuilder()
//                                                .setTitle("运机械").addExtras(ext)
//                                                .build())
//                                .addPlatformNotification(
//                                        IosNotification.newBuilder()
//                                                .setSound("default")
//                                                .addExtras(ext).build())
//                                .build()).build();
//    }

//    /**
//     * 按别名发送到单个用户
//     *
//     * @param alias
//     * @param msg
//     * @param page
//     * @param time
//     * @return 0:不成功 1:android成功,IOS不成功 2:IOS成功,android不成功 3.都成功
//     */
//    public static long pushByAlias(long alias, String msg, int page, int time) {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("type", String.valueOf(page));
//        long l = 0;
//        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
//        try {
//            PushPayload pushPayload = PushPayload
//                    .newBuilder()
//                    .setOptions(
//                            Options.newBuilder()
//                                    .setApnsProduction(apnsProduction).build())
//                    .setOptions(
//                            Options.newBuilder().setBigPushDuration(time)
//                                    .build()).setPlatform(Platform.android())
//                    .setAudience(Audience.alias(String.valueOf(alias)))
//                    .setNotification(Notification.android(msg, null, map))
//                    .build();
//            l += 1;
//        } catch (Exception e) {
//        }
//        try {
//            PushPayload pushPayload = PushPayload
//                    .newBuilder()
//                    .setPlatform(Platform.ios())
//
//                    .setOptions(
//                            Options.newBuilder().setBigPushDuration(time)
//                                    .build())
//                    .setAudience(Audience.alias(String.valueOf(alias)))
//                    .setNotification(
//                            Notification
//                                    .newBuilder()
//                                    .addPlatformNotification(
//                                            IosNotification.newBuilder()
//                                                    .setSound("default")
//                                                    .setAlert(msg)
//                                                    .addExtras(map).build())
//                                    .build())
//                    .setOptions(
//                            Options.newBuilder()
//                                    .setApnsProduction(apnsProduction).build())
//                    .build();
//            PushResult result = jpushClient.sendPush(pushPayload);
//            System.out.println("result ios=" + result.isResultOK());
//            l += 2;
//        } catch (Exception e) {
//            // e.printStackTrace();
//            // e.printStackTrace();
//            // ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            // e.printStackTrace(new PrintStream(baos));
//            // String exception = baos.toString();
//            // System.out.println("exception:" + exception);
//        }
//        return l;
//    }




//    /**
//     * 按别名发送到单个用户
//     *
//     * @param msg
//     * @param page
//     *            (1,邀请消息 2,报警消息   3,版本消息)
//     * @param time
//     *            (延迟时间 分钟)
//     * @return 0:不成功 1:android成功,IOS不成功 2:IOS成功,android不成功 3.都成功
//     */
//    public static long pushVersion(String msg, int page, int time) {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("type", String.valueOf(page));
//        long l = 0;
//
//        try {
//            JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
//            PushPayload pushPayload = PushPayload
//                    .newBuilder()
//                    .setOptions(
//                            Options.newBuilder()
//                                    .setApnsProduction(apnsProduction).build())
//                    .setOptions(
//                            Options.newBuilder().setBigPushDuration(time)
//                                    .build()).setPlatform(Platform.android())
//                    .setAudience(Audience.all())
//                    .setNotification(Notification.android(msg, "云机械", map))
//                    .build();
//            PushResult result = jpushClient.sendPush(pushPayload);
//            l += 1;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return l;
//    }

//    /**
//     * 按别名发送到单个用户  根据app名称
//     *
//     * @param msg
//     * @param page
//     *            (1,邀请消息 2,报警消息   3,版本消息)
//     * @param time
//     *            (延迟时间 分钟)
//     * @return 0:不成功 1:android成功,IOS不成功 2:IOS成功,android不成功 3.都成功
//     */
//    public static long pushVersionAZB(String msg, int page, int time) {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("type", String.valueOf(page));
//        long l = 0;
//
//        try {
//            JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
//            PushPayload pushPayload = PushPayload
//                    .newBuilder()
//                    .setOptions(
//                            Options.newBuilder()
//                                    .setApnsProduction(apnsProduction).build())
//                    .setOptions(
//                            Options.newBuilder().setBigPushDuration(time)
//                                    .build()).setPlatform(Platform.android())
//                    .setAudience(Audience.all())
//                    .setNotification(Notification.android(msg, "安装宝", map))
//                    .build();
//            PushResult result = jpushClient.sendPush(pushPayload);
//            l += 1;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return l;
//    }

//    /**
//     * 延迟按别名发送到多个用户
//     *
//     * @param alias
//     *            目标
//     * @param msg
//     *            消息体
//     * @param type
//     *            (1,邀请消息 2,报警消息 3,版本消息)
//     * @param time
//     *            延迟时间(分钟)
//     * @return 0:不成功 1:android成功,IOS不成功 2:IOS成功,android不成功 3.都成功
//     */
//    public static long pushByAlias(List<String> alias, String msg, int type,
//                                   long deviceId, String nickName, int time, String macAddress) {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("type", String.valueOf(type));
//        map.put("deviceId", String.valueOf(deviceId));
//        map.put("nickName", nickName);
//        map.put("macAddress", macAddress);
//        long l = 0;
//        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
//        try {
//            PushPayload pushPayload = PushPayload
//                    .newBuilder()
//                    .setOptions(
//                            Options.newBuilder()
//                                    .setApnsProduction(apnsProduction).build())
//                    .setOptions(
//                            Options.newBuilder().setBigPushDuration(time)
//                                    .build()).setPlatform(Platform.android())
//                    .setAudience(Audience.alias(alias))
//                    .setNotification(Notification.android(msg, "云机械", map))
//                    .build();
//            PushResult result = jpushClient.sendPush(pushPayload);
//            l += 1;
//        } catch (Exception e) {
//
//        }
//        try {
//            PushPayload pushPayload = PushPayload
//                    .newBuilder()
//                    .setPlatform(Platform.ios())
//
//                    .setOptions(
//                            Options.newBuilder().setBigPushDuration(time)
//                                    .build())
//                    .setAudience(Audience.alias(alias))
//                    .setNotification(
//                            Notification
//                                    .newBuilder()
//                                    .addPlatformNotification(
//                                            IosNotification.newBuilder()
//                                                    .setSound("default")
//                                                    .setAlert(msg)
//                                                    .addExtras(map).build())
//                                    .build())
//                    .setOptions(
//                            Options.newBuilder()
//                                    .setApnsProduction(apnsProduction).build())
//                    .build();
//            // System.out.print(pushPayload.toJSON());
//            PushResult result = jpushClient.sendPush(pushPayload);
//            l += 2;
//        } catch (Exception e) {
//
//        }
//        return l;
//    }




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
//        try {
            PushPayload pushPayload = PushPayload
                    .newBuilder()
                    .setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build())
                    .setOptions(Options.newBuilder().setBigPushDuration(time).build())
                    .setPlatform(Platform.android())
                    .setAudience(Audience.alias(String.valueOf(alias)))
                    .setNotification(Notification.android(msg, title, map))
                    .build();
            PushResult result = jpushClient.sendPush(pushPayload);
            if(null!=result && result.isResultOK()==true){//推送成功，返回true，l+1
                if(log.isInfoEnabled()){
                    log.info("result android={}" , result.isResultOK());
                }
                l += 1;
            }
//        } catch (Exception e) {
//            log.error("Jpush send android is error ==>{}",e);
//            throw new BusinessCheckFailException(BaseErrorEnum.SYS_ERROR);
//        }
//        try {
            PushPayload pushPayloadIOS = PushPayload
                    .newBuilder()
                    .setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build())
                    .setOptions(Options.newBuilder().setBigPushDuration(time).build())
                    .setPlatform(Platform.ios())
                    .setAudience(Audience.alias(String.valueOf(alias)))
                    .setNotification(
                            Notification.newBuilder().addPlatformNotification(
                                            IosNotification.newBuilder()
                                                    .setSound("default")
                                                    .setAlert(msg)
                                                    .addExtras(map).build())
                                    .build())
                    .build();
            PushResult resultIOS = jpushClient.sendPush(pushPayloadIOS);
            if(null!=resultIOS && resultIOS.isResultOK()==true){//推送成功，返回true，l+2
                if(log.isInfoEnabled()){
                    log.info("result ios={}" , resultIOS.isResultOK());
                }
                l += 2;
            }
//        } catch (Exception e) {
//            log.error("Jpush send ios is error ==>{}",e);
//            throw new BusinessCheckFailException(BaseErrorEnum.SYS_ERROR);
//        }
        return l;
    }
}
