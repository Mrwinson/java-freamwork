package com.cloudm.framework.common.web.result;
import com.cloudm.framework.common.web.result.base.BaseResult;
import com.cloudm.framework.common.web.result.base.ServiceError;

import java.io.Serializable;

/**
 * @description: 返回result对象
 * @author: Courser
 * @date: 2017/3/15
 * @version: V1.0
 */
public class Result<T> extends BaseResult implements Serializable{
    private static final long serialVersionUID = -7647570604845078925L;


    /**
     * 成功返回的数据
     */
    private T data;

    public static <D> Result<D> wrapSuccessfulResult(D data) {
        Result<D> result = new Result<D>();
        result.data = data;
        result.success = true;
        result.code=SUCCESS_CODE ;
        return result;
    }

    /**
     * 错误信息返回
     * @param error
     * @param <D>
     * @return
     */
    public static <D> Result<D> wrapErrorResult(ServiceError error) {
        Result<D> result = new Result<D>();
        result.success = false;
        result.code = error.getCode();
        result.message = error.getMessage();
        return result;
    }




    /**
     * 错误返回
     * @param code 错误码
     * @param message 错误信息
     * @param <D> 泛型
     * @return
     */
    public static <D> Result<D> wrapErrorResult(String code, String message) {
        Result<D> result = new Result<D>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }
}
