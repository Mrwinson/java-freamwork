package com.cloudm.framework.common.web.result;

import com.cloudm.framework.common.enums.BaseBizEnum;
import com.cloudm.framework.common.web.result.base.BaseResult;
import com.cloudm.framework.common.web.result.base.ServiceError;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页的Result对象
 * @author: Courser
 * @date: 2017/3/23
 * @version: V1.0
 */
public class PagingResult<T> extends BaseResult implements Serializable {
    private static final long serialVersionUID = 8911072786251958689L;
    @Getter
    @Setter
    private List<T> list ;//数据集合
    @Getter
    @Setter
    private  int total ;//总记录数

    /**
     * 成功返回列表数据
     * @param data 列表数据
     * @param total 在此条件先可以有多少个数据可以满足
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> wrapSuccessfulResult(List<T> data, int total) {
        PagingResult<T> result = new PagingResult<>();
        result.list = data;
        result.total = total;
        result.success = true;
        result.code = String.valueOf(BaseBizEnum.FRIST.getCode().intValue());
        return result;
    }

    /**
     *  查询分页失败
     * @param error
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> wrapErrorResult(ServiceError error) {
        PagingResult<T> result = new PagingResult<>();
        result.success = false;
        result.code = error.getCode();
        result.message = error.getMessage();
        return result;
    }

    /**
     * 分页失败
     * @param code 错误码
     * @param message 错误信息
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> wrapErrorResult(String code, String message) {
        PagingResult<T> result = new PagingResult<>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }
}
