package com.cloudm.framework.common.enums;

import com.cloudm.framework.common.web.result.base.ServiceError;

/**
 * @description: 基础的异常异常枚举
 * @author: Courser
 * @date: 2017/3/20
 * @version: V1.0
 */
public enum BaseErrorEnum implements ServiceError {
    VALIDATE_ERROR("-10","认证失败"),
    SYS_ERROR("-1","系统异常"),
    UNKNOWN_ERROR("-1","未知异常");
    private final String code;
    private final String message ;

    BaseErrorEnum(String code ,String message){
        this.code = code;
        this.message = message ;
    }

    /**
     * 错误码
     *
     * @return 返回错误码
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * 返回错误信息
     *
     * @return 错误信息
     */
    @Override
    public String getMessage() {
        return message;
    }
}
