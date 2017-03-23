package com.cloudm.framework.common.param;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description:
 * @author: Courser
 * @date: 2017/3/23
 * @version: V1.0
 */
@Setter
@Getter
public class BasePageQueryParam {
    /**
     * 排序
     */
    private List<String> sorts;
    /**
     * 限制条数
     */
    private int limit;
    /**
     * 限制开始条数
     */
    private int offset;
}
