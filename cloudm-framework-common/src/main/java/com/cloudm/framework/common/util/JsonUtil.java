package com.cloudm.framework.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
//     * 将json字符串转为List
//     * @param json
//     * @param tClass
//     * @param <T>
//     * @return
//     */
//    public static <T> T fromJsonToList(String json, Class<T> tClass) {
//          new Gson().fromJson(json, tClass);
//
//    }
    /**
     * 将json字符串转为List
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJsonToList(String json, Type type) {
        return  new Gson().fromJson(json, type);

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


    /**
     * 打印输入到控制台
     * @param jsonStr
     */
    public static void printJson(String jsonStr){
        System.out.println(formatJson(jsonStr));
    }

    /**
     * 格式化
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    public static void main(String[] args) {
        List<DemoList> list = new ArrayList<>( );
        for(int i=0 ;i<5;i++){
            DemoList demo = new DemoList();
            demo.setId(i);
            demo.setName("name_"+i);
            demo.setNow(new Date());
            list.add(demo);
        }
        String json  = JsonUtil.toJson(list);
        Type type = new TypeToken<ArrayList<DemoList>>() {}.getType();
        List<DemoList> list2 = new ArrayList<>( );
        list2 =JsonUtil.fromJsonToList(json,type);
        System.out.println(JsonUtil.toJson(list2));
    }


}
@Data
class DemoList{
    private String name ;
    private Integer id ;
    private java.util.Date now ;
}
