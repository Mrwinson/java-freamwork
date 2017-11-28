package com.cloudm.framework.common.util;

import com.cloudm.framework.common.enums.BaseErrorEnum;
import com.cloudm.framework.common.ex.BusinessProcessFailException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desoription http工具类
 * @author: leo
 * @date: 2017/11/28
 * @version: V1.0
 */
public class HttpClientUtil {


    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);
            cm.setDefaultMaxPerRoute(5);
        }
    }

    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    public static String httpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> params) {
        return httpGetRequest(url, null, params);
    }

    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params) {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(ub.build());
        } catch (URISyntaxException e) {
            log.error(ClassUtil.getExcCodeError(HttpUtil.class) + ",{}!构建连接错误", url);
            throw new BusinessProcessFailException(BaseErrorEnum.SYS_ERROR);
        }
        if (headers != null) {
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpGet.addHeader((String) param.getKey(), String.valueOf(param.getValue()));
            }
        }
        return getResult(httpGet);
    }

    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> params) {
        return httpPostRequest(url, null, params);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(url);
        if (headers != null) {
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpPost.addHeader((String) param.getKey(), String.valueOf(param.getValue()));
            }
        }
        Object pairs = covertParams2NVPS(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity((List) pairs, UTF_8));
        } catch (UnsupportedEncodingException e) {
            log.error(ClassUtil.getExcCodeError(HttpUtil.class) + ",{}!设置编码错误", url);
            throw new BusinessProcessFailException(BaseErrorEnum.SYS_ERROR);
        }
        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair((String) param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }

    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity, "utf-8");

                    response.close();

                    return result;
                }
            }
            throw new BusinessProcessFailException(BaseErrorEnum.RPC_ERROR);
        } catch (BusinessProcessFailException bpfe) {
            log.error(ClassUtil.getExcCodeError(HttpUtil.class) + ",{}! 远程处理异常", request.getURI().toString());
            throw bpfe;
        } catch (IOException ioe) {
            log.error(ClassUtil.getExcCodeError(HttpUtil.class) + ",{}! 连接出错，可能远程服务器挂掉了", request.getURI().toString());
            throw new BusinessProcessFailException(BaseErrorEnum.SYS_ERROR);
        } catch (Exception e) {
            log.error(ClassUtil.getExcCodeError(HttpUtil.class));
            throw new BusinessProcessFailException(BaseErrorEnum.SYS_ERROR);
        }
    }
}

