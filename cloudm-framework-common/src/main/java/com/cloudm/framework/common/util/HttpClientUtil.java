package com.cloudm.framework.common.util;

import com.cloudm.framework.common.enums.BaseErrorEnum;
import com.cloudm.framework.common.ex.BusinessProcessFailException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @description: http 远程接口调用
 * @author: Courser
 * @date: 2017/7/1
 * @version: V1.0
 */
@Slf4j
public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// 整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     *普通的get 请求，参数在url中拼装
     * @param url
     * @return
     */
    public static String httpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    /**
     * get 请求带有map参数结构
     * @param url
     * @param params
     * @return
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, Object> params) {
       return  httpGetRequest(url,null,params);
    }

    /**
     * get 请求 带有 heards，参数都是map的形式
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params) {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

                 HttpGet httpGet = null;
                 try {
                     httpGet = new HttpGet(ub.build());
                 } catch (URISyntaxException e) {
                     log.error(ClassUtil.getExcCodeError(HttpUtil.class)+",{}！构建连接错误",url);
                     throw  new BusinessProcessFailException(BaseErrorEnum.SYS_ERROR);
                 }
                 if(headers != null){
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
            }
        }

        return getResult(httpGet);
    }

    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    /**
     *  post 请求，参数是map形式
     * @param url
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String httpPostRequest(String url, Map<String, Object> params)  {
        return httpPostRequest(url,null,params);
    }

    /**
     * post 请求，带有Hearder参数
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
             {
        HttpPost httpPost = new HttpPost(url);

        if (headers !=null){
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
            }
        }


        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
                 try {
                     httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
                 } catch (UnsupportedEncodingException e) {
                     log.error(ClassUtil.getExcCodeError(HttpUtil.class)+",{}！设置编码错误",url);
                     throw  new BusinessProcessFailException(BaseErrorEnum.SYS_ERROR);
                 }

                 return getResult(httpPost);
    }

    /**
     * 把Map中的参数封装成 NameValuePair
     * @param params
     * @return
     */
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }

        return pairs;
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request) {
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//成功返回
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // long len = entity.getContentLength();// -1 表示长度未知
                    String result = EntityUtils.toString(entity,"utf-8");

                    response.close();
//                    httpClient.close();

                    return result;
                }
            }

            //返回非200的状态抛远程调用异常，说明我们连接有问题了
            throw new BusinessProcessFailException(BaseErrorEnum.RPC_ERROR);


        } catch (BusinessProcessFailException bpfe){
            log.error(ClassUtil.getExcCodeError(HttpUtil.class)+",{}！远程处理异常",request.getURI().toString());
            throw bpfe;
        }catch (IOException ioe){
            log.error(ClassUtil.getExcCodeError(HttpUtil.class)+",{}！连接出错，可能远程服务器挂掉了",request.getURI().toString());
            throw  new BusinessProcessFailException(BaseErrorEnum.SYS_ERROR);
        }
        catch (Exception e) {
            log.error(ClassUtil.getExcCodeError(HttpUtil.class));
            throw  new BusinessProcessFailException(BaseErrorEnum.SYS_ERROR);
        }


    }

}