package com.cloudm.framework.common.util;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: Json 工具类
 * @author: Courser
 * @date: 2017/3/15
 * @version: V1.0
 */
public class JsonUtil {

    /**
     * 将POJO 转为 JSON
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj){
        return  new Gson().toJson(obj) ;
    }

    /**
     * JSON 转POJO
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type){
       return new Gson().fromJson(json,type);
    }

}
