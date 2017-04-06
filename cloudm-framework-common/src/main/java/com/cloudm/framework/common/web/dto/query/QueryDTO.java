package com.cloudm.framework.common.web.dto.query;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 最基础的查询类
 * @author: Courser
 * @date: 2017/4/6
 * @version: V1.0
 */
@Data
public class QueryDTO implements Serializable {
    /**
     * ID
     */
    private Integer id ;
    /**
     * 创建人
     */
    private Integer creator;
    /**
     * 操作人
     */
    private Integer operator ;
    /**
     * 开始时间
     */
    private Date beginTime ;
    /**
     * 结束时间
     */
    private Date endTime ;
    /**
     * 是否删除
     */
    private Integer yn  ;
    /**
     * 启动状态
     */
    private  Integer enabledState ;
}
