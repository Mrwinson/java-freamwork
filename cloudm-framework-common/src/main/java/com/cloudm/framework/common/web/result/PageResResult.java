package com.cloudm.framework.common.web.result;

import com.cloudm.framework.common.web.result.base.ServiceError;
import com.cloudm.framework.common.web.result.page.Page;
import org.springframework.data.domain.Pageable;

/**
 * @description: 类描述
 * @author: wangbo
 * @date: 2017/12/14
 * @version: V1.0
 */
public class PageResResult<T> extends ResResult<T>{

    private static final long serialVersionUID = -7647570604845078925L;
    /**
     * 分页信息
     */
    private Page page ;
    /**
     * 给开发人员的提示
     */
    private String devMsg;

    public static <D> ResResult<D> wrapSuccessfulResult(D data) {
        ResResult<D> result = new ResResult<>();
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
    public static <D> ResResult<D> wrapSuccessfulResult(D data,String message) {
        ResResult<D> result = new ResResult<>();
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
    public static  <D> PageResResult<D> wrapSuccessfulResult(D data, Pageable pageable, int total){
        Page springPage = new Page(pageable,total);
        PageResResult<D> result = new PageResResult<>();
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
    public static  <D> PageResResult<D> wrapSuccessfulResult(D data, Pageable pageable, int total, String message){
        Page springPage = new Page(pageable,total);
        PageResResult<D> result = new PageResResult<>();
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
    public static <D> ResResult<D> wrapErrorResult(ServiceError error) {
        ResResult<D> result = new ResResult<>();
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
    public static <D> ResResult<D> wrapErrorResult(Integer code, String message) {
        ResResult<D> result = new ResResult<>();
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
    public static <D> ResResult<D> wrapErrorResult(D data,Integer code, String message) {
        ResResult<D> result = new ResResult<>();
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
