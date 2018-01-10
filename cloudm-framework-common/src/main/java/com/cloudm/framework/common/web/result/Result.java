package com.cloudm.framework.common.web.result;
import com.cloudm.framework.common.web.result.base.BaseResult;
import com.cloudm.framework.common.web.result.base.ServiceError;
import com.cloudm.framework.common.web.result.page.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * @description: 返回result对象
 * @author: Courser
 * @date: 2017/3/15
 * @version: V1.0
 */
public class Result<T> extends BaseResult<T> implements Serializable{
    private static final long serialVersionUID = -7647570604845078925L;
    /**
     * 分页信息
     */
    private Page page ;
    /**
     * 给开发人员的提示
     */
    private String devMsg;

    public static <D> BaseResult<D> wrapSuccessfulResult(D data) {
        BaseResult<D> result = new BaseResult<>();
        result.setResult(data);
        result.setSuccess(true);
        result.setCode(SUCCESS_CODE);
        return result;
    }

    /**
     * 返回成功，带提示信息
     * @param data
     * @param message
     * @param <D>
     * @return
     */
    public static <D> BaseResult<D> wrapSuccessfulResult(D data,String message) {
        BaseResult<D> result = new BaseResult<>();
        result.setResult(data);
        result.setSuccess(true);
        result.setCode(SUCCESS_CODE);
        result.setMessage(message);
        return result;
    }
    /**
     * 成功返回数据列表
     * @param data 数据对象
     * @param pageable 分页参数
     * @param total 总条数
     * @param <D>
     * @return list集合
     */
    public static  <D> Result<D> wrapSuccessfulResult(D data, Pageable pageable, int total){
        Page springPage = new Page(pageable,total);
        Result<D> result = new Result<>();
        result.setResult(data);
        result.setSuccess(true);
        result.setCode(SUCCESS_CODE);
        result.page = springPage ;
        return  result ;
    }
    /**
     * 成功返回数据列表,带提示信息
     * @param data 数据对象
     * @param pageable 分页参数
     * @param total 总条数
     * @param <D>
     * @return list集合
     */
    public static  <D> Result<D> wrapSuccessfulResult(D data, Pageable pageable, int total,String message){
        Page springPage = new Page(pageable,total);
        Result<D> result = new Result<>();
        result.setResult(data);
        result.setSuccess(true);
        result.setCode(SUCCESS_CODE);
        result.setMessage(message);
        result.page = springPage ;
        return  result ;
    }
    /**
     * 错误信息返回
     * @param error
     * @param <D>
     * @return
     */
    public static <D> BaseResult<D> wrapErrorResult(ServiceError error) {
        BaseResult<D> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(error.getCode());
        result.setMessage(error.getMessage());
        return result;
    }




    /**
     * 错误返回
     * @param code 错误码
     * @param message 错误信息
     * @param <D> 泛型
     * @return
     */
    public static <D> BaseResult<D> wrapErrorResult(Integer code, String message) {
        BaseResult<D> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    /**
     * 错误返回,保存错误的数据
     * @param code 错误码
     * @param message 错误信息
     * @param <D> 泛型
     * @return
     */
    public static <D> BaseResult<D> wrapErrorResult(D data,Integer code, String message) {
        BaseResult<D> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        result.setResult(data);
        return result;
    }
    public Page getPage(){
        return  page;
    }

    /**
     *  返回开发人员的提示信息
     * @return 信息
     */
    public String getDevMsg() {
        return devMsg;
    }

    /**
     * 设置开发人员提示信息
     * @param devMsg
     */
    public void setDevMsg(String devMsg) {
        this.devMsg = devMsg;
    }
}
