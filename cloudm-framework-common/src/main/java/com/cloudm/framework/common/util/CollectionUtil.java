package com.cloudm.framework.common.util;

import com.cloudm.framework.common.enums.BaseBizEnum;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @description: 集合工具类
 * @author: Courser
 * @date: 2017/4/6
 * @version: V1.0
 */
public class CollectionUtil {
    /**
     * 判断集合是否为空
     * @param collection 集合类
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断map是否为空
     * @param map 要判断的map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map){
        return CollectionUtils.isEmpty(map);
    }

    /**
     * 判断集合是否不为空
     * @param collection 集合类
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断map是否不为空
     * @param map 要判断的map
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> map){
        return !CollectionUtils.isEmpty(map);
    }
    /**
     * 创建List对象
     *
     * @param <E> 泛型，
     * @return
     */
    public static <E> ArrayList<E> newArrayList() {
        return Lists.newArrayList();
    }
    /**
     * 获取List集合中第一个对象，前提是自己先判断这个list不会为空
     *
     * @param list
     * @param <E>
     * @return
     */
    public static <E> E firstEntity(List<E> list) {
        return list.get(BaseBizEnum.ZERRO.getCode());
    }
}
