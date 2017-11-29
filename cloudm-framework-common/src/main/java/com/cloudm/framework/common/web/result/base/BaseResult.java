package com.cloudm.framework.common.web.result.base;
import com.cloudm.framework.common.enums.BaseBizEnum;

import java.io.Serializable;

/**
 * @description: 基础 result
 * @author: Courser
 * @date: 2017/3/15
 * @version: V1.0
 */
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = -4205541359679710511L;
    protected static final Integer SUCCESS_CODE= BaseBizEnum.OK.getCode();
    /**
     * 是否成功
     */
    protected Boolean success;

    /**
     * 返回码
     */
    protected Integer code;

    /**
     * 返回信息
     */
    protected String message;

    /**
     * 成功返回的数据
     */
    protected T result;
    /**
     * 是否成功
     * @return true成功，false失败
     */
    public Boolean isSuccess() {
        return success;
    }

    /**
     * 设置是否成功
     * @param success false：失败，true：成功
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * 返回码
     * @return 返回码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置返回码
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回信息
     * @return 可能是成功提示信息，也可能是错误提示信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置信息
     * @param message 信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获得结果对象
     * @return
     */
    public T getResult() {
        return result;
    }

    /**
     * 设置结果对象
     * @param result
     */
    public void setResult(T result) {
        this.result = result;
    }
}
