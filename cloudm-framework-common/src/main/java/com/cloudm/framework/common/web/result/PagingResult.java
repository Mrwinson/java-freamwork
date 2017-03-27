package com.cloudm.framework.common.web.result;

import com.cloudm.framework.common.enums.BaseBizEnum;
import com.cloudm.framework.common.util.PageUtil;
import com.cloudm.framework.common.web.result.base.BaseResult;
import com.cloudm.framework.common.web.result.base.ServiceError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页的Result对象
 * @author: Courser
 * @date: 2017/3/23
 * @version: V1.0
 */
@Getter
@Setter
public class PagingResult<T> extends BaseResult implements Serializable {
    private static final long serialVersionUID = 8911072786251958689L;

    private List<T> result ;//数据集合

    private  int total ;//总记录数
    private boolean first ;//是否首页
    private boolean last;//是否尾页
    private int totalPage;//总共几页


    /**
     * 成功返回列表数据
     * @param data 列表数据
     * @param total 在此条件先可以有多少个数据可以满足
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> wrapSuccessfulResult(List<T> data, int total) {
        PagingResult<T> result = new PagingResult<>();
        result.result = data;
        result.total = total;
        result.success = true;
        result.code = BaseBizEnum.OK.getCode();
        return result;
    }

    /**
     * 成功返回数据列表
     * @param data 数据对象
     * @param pageable 分页参数
     * @param total 总条数
     * @param <T>
     * @return list集合
     */
    public static  <T> PagingResult<T> wrapSuccessfulResult(List<T> data,Pageable pageable,int total){
        Page page = PageUtil.newPage(data,pageable,total);
        PagingResult<T> result = new PagingResult<>();
        result.result = page.getContent();
        result.first=page.isFirst();
        result.last = page.isLast() ;
        result.total = Integer.valueOf(String.valueOf(page.getTotalElements()));
        result.totalPage= page.getTotalPages();
        return  result ;
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
    public static <T> PagingResult<T> wrapErrorResult(Integer code, String message) {
        PagingResult<T> result = new PagingResult<>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }
//    /**
//     * 分页失败
//     * @param code 错误码
//     * @param message 错误信息
//     * @param <T>
//     * @return
//     */
//    public static <T> PagingResult<T> wrapErrorResult(ServiceError error,Page page) {
//        PagingResult<T> result = new PagingResult<>();
//        result.success = false;
//        result.code = code;
//        result.message = message;
//        return result;
//    }
}
