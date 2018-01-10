package com.cloudm.framework.common.web.result;

import com.cloudm.framework.common.util.PageUtil;
import com.cloudm.framework.common.web.result.base.BaseResultStale;
import com.cloudm.framework.common.web.result.base.ServiceError;
import com.google.common.collect.Lists;
import org.springframework.data.domain.*;

/**
 * @description: 类描述
 * @author: wangbo
 * @date: 2017/12/14
 * @version: V1.0
 */
public class ResultStale<T> extends BaseResultStale {

    private static final long serialVersionUID = -7647570604845078925L;


    /**
     * 成功返回的数据
     */
    private T ResultStale;
    private Page page ;

    public static <D> ResultStale<D> wrapSuccessfulResultStale(D data) {
        ResultStale<D> ResultStale = new ResultStale<D>();
        ResultStale.ResultStale = data;
        ResultStale.success = true;
        ResultStale.code=SUCCESS_CODE ;
        return ResultStale;
    }

    /**
     * 返回成功，带提示信息
     * @param data
     * @param message
     * @param <D>
     * @return
     */
    public static <D> ResultStale<D> wrapSuccessfulResultStale(D data,String message) {
        ResultStale<D> ResultStale = new ResultStale<D>();
        ResultStale.ResultStale = data;
        ResultStale.success = true;
        ResultStale.code=SUCCESS_CODE ;
        ResultStale.setMessage(message);
        return ResultStale;
    }
    /**
     * 成功返回数据列表
     * @param data 数据对象
     * @param pageable 分页参数
     * @param total 总条数
     * @param <D>
     * @return list集合
     */
    public static  <D> ResultStale<D> wrapSuccessfulResultStale(D data, Pageable pageable, int total){
        org.springframework.data.domain.Page springPage = PageUtil.newPage(Lists.newArrayList(),pageable,total);
        ResultStale<D> ResultStale =wrapSuccessfulResultStale(data);
        ResultStale.page = springPage ;
        return  ResultStale ;
    }
    /**
     * 成功返回数据列表,带提示信息
     * @param data 数据对象
     * @param pageable 分页参数
     * @param total 总条数
     * @param <D>
     * @return list集合
     */
    public static  <D> ResultStale<D> wrapSuccessfulResultStale(D data, Pageable pageable, int total,String message){
        org.springframework.data.domain.Page springPage = PageUtil.newPage(Lists.newArrayList(),pageable,total);
        ResultStale<D> ResultStale =wrapSuccessfulResultStale(data,message);
        ResultStale.page = springPage ;
        return  ResultStale ;
    }
    /**
     * 错误信息返回
     * @param error
     * @param <D>
     * @return
     */
    public static <D> ResultStale<D> wrapErrorResultStale(ServiceError error) {
        ResultStale<D> ResultStale = new ResultStale<D>();
        ResultStale.success = false;
        ResultStale.code = error.getCode();
        ResultStale.message = error.getMessage();
        return ResultStale;
    }




    /**
     * 错误返回
     * @param code 错误码
     * @param message 错误信息
     * @param <D> 泛型
     * @return
     */
    public static <D> ResultStale<D> wrapErrorResultStale(Integer code, String message) {
        ResultStale<D> ResultStale = new ResultStale<D>();
        ResultStale.success = false;
        ResultStale.code = code;
        ResultStale.message = message;
        return ResultStale;
    }
    /**
     * 错误返回,保存错误的数据
     * @param code 错误码
     * @param message 错误信息
     * @param <D> 泛型
     * @return
     */
    public static <D> ResultStale<D> wrapErrorResultStale(D data,Integer code, String message) {
        ResultStale<D> ResultStale = new ResultStale<D>();
        ResultStale.ResultStale = data ;
        ResultStale.success = false;
        ResultStale.code = code;
        ResultStale.message = message;
        return ResultStale;
    }
    public T getResultStale() {
        return ResultStale;
    }
    public org.springframework.data.domain.Page getPage(){
        return  page;
    }

}
