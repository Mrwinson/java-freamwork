package com.cloudm.framework.web.result.base;

/**
 * @description: 错误数据对象接口
 * @author: Courser
 * @date: 2017/3/15
 * @version: V1.0
 */
public interface ServiceError {
    /**
     * 错误码
     * @return 返回错误码
     */
    String getCode();

    /**
     * 返回错误信息
     * @return 错误信息
     */
    String getMessage();


}
