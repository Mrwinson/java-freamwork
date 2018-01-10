package com.cloudm.framework.common.web.result.page;

import org.springframework.data.domain.Pageable;

/**
 * @description: 分页对象
 * @author: wangbo
 * @date: 2017/12/14
 * @date: 2017/11/23
 * @version: V1.0
 */
public class Page {

    /**
     * 总数
     */
    private int total;
    /**
     * 每页大小
     */
    private int size;
    /**
     * 当前页码
     */
    private int page;

    public Page(Pageable pageable, int total){
        this.size = pageable.getPageSize();
        this.total = total;
        this.page = pageable.getPageNumber();
    }

    public int getTotalElements() {
        return total;
    }

    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    public int getSize() {
        return size;
    }

    public int getPage() {
        return page;
    }

}
