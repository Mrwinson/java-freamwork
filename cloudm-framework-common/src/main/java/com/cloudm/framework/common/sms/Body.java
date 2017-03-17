package com.cloudm.framework.common.sms;

/**
 * @description:
 * @author: Courser
 * @date: 2017/3/17
 * @version: V1.0
 */
public class Body {
    private Result result;
    private String request_id;
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }
    public String getRequest_id() {
        return request_id;
    }
    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
}