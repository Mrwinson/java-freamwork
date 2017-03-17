package com.cloudm.framework.common.util;

import org.springframework.beans.BeanUtils;

/**
 * @description: bean的工具类
 * @author: Courser
 * @date: 2017/3/17
 * @version: V1.0
 */
public class BeanUtil {

    /**
     * 对象属性值复制，这个是浅复制
     * @param source 有值对象，即源
     * @param target 没有值得对象，即目标对象
     */
    public static void copyProperties(Object source, Object target){
        BeanUtils.copyProperties(source,target);
    }
}
