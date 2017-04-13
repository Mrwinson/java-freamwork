package com.cloudm.framework.common.core;

/**
 * @description:  用于枚举key-value映射描述
 * @author: Courser
 * @date: 2017/4/13
 * @version: V1.0
 */
public interface KeyedNamed {
    /**
     * 状态值
     */
    Integer getKey();

    /**
     * 状态描述
     */
    String getName();
}
