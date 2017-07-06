package com.cloudm.framework.common.web.result;

import java.io.Serializable;
import java.util.List;

/**
 * 接口分页查询统一封装对象
 * Created by jackson on 2017/6/26.
 */
public class ResultPageDO<T>  implements Serializable {
    private boolean ok;
    private int code;
    private String message;
    private long count;//分页查询count总数
    private List<T> list;//分页查询结果集

    public boolean isOk() {
        return ok;
    }
    public void setOk(boolean ok) {
        this.ok = ok;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
}
