package com.cloudm.framework.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jackson on 2017/6/26.
 */
@Slf4j
public class HFCustUtils {

    public static String sendObject(String urlvalue,Object bean) {
        String recString = "";
        try {
            StringBuilder params = new StringBuilder();
            //拼接参数
            //得到类对象
            Class userCla = (Class) bean.getClass();
	        /*
	         * 得到类中的所有属性集合
	         */
            Field[] fs = userCla.getDeclaredFields();
            for(int i = 0 ; i < fs.length; i++){
                Field f = fs[i];
                f.setAccessible(true); //设置些属性是可以访问的
                try {
                    Object val = f.get(bean);//得到此属性的值
                    if (val!=null && !"".equals(val)) {
                        params.append("&"+f.getName()+"="+ val);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            urlConnection.connect();
            // post信息 ,这步很重要,不然就乱码
            OutputStream os = urlConnection.getOutputStream();
            os.write(params.toString().substring(1).getBytes("UTF-8"));
            os.close();
            int iHttpResult = urlConnection.getResponseCode();
            if (iHttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(), "UTF-8"));
                StringBuffer sb = new StringBuffer();

                int ch;
                while ((ch = rd.read()) > -1) {
                    sb.append((char) ch);
                }
                recString = sb.toString();
            } else {
                log.error("网络连接错误,{}", iHttpResult);
            }
        } catch (Exception e) {
            log.error("send sms fail:", e);
        }
        return recString;
    }

    public static String send(String urlvalue,String mobile) {
        String recString = "";
        try {
            StringBuilder params = new StringBuilder();
            params.append("cust_tel=" + mobile);
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            urlConnection.connect();
            // post信息 ,这步很重要,不然就乱码
            OutputStream os = urlConnection.getOutputStream();
            os.write(params.toString().getBytes("UTF-8"));
            os.close();
            int iHttpResult = urlConnection.getResponseCode();
            if (iHttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(), "UTF-8"));
                StringBuffer sb = new StringBuffer();
                int ch;
                while ((ch = rd.read()) > -1) {
                    sb.append((char) ch);
                }
                recString = sb.toString();
            } else {
                log.error("网络连接错误,{}", iHttpResult);
            }
        } catch (Exception e) {
            log.error("send sms fail:", e);
        }
        return recString;
    }

    public static String sendNoParam(String urlvalue) {
        String recString = "";
        try {
            StringBuilder params = new StringBuilder();
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            urlConnection.connect();
            // post信息 ,这步很重要,不然就乱码
            OutputStream os = urlConnection.getOutputStream();
            os.write(params.toString().getBytes("UTF-8"));
            os.close();
            int iHttpResult = urlConnection.getResponseCode();
            if (iHttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(), "UTF-8"));
                StringBuffer sb = new StringBuffer();
                int ch;
                while ((ch = rd.read()) > -1) {
                    sb.append((char) ch);
                }
                recString = sb.toString();
            } else {
                log.error("网络连接错误,{}", iHttpResult);
            }
        } catch (Exception e) {
            log.error("send sms fail:", e);
        }
        return recString;
    }
}
