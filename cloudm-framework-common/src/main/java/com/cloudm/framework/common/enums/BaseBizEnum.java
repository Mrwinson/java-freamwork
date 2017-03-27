package com.cloudm.framework.common.enums;

/**
 * @description: 业务需求的常亮
 * @author: Courser
 * @date: 2017/3/21
 * @version: V1.0
 */
public enum  BaseBizEnum {
    ZERRO(0, "阿拉伯数字0"),
    FRIST(1, "阿拉伯数字1"),
    SECOND(2, "阿里伯数字2"),
    YN_Y(1, "已删除"),
    YN_N(0, "未删除"),
    DEFAULT_USER(0,"系统管理员"),
    SAVE_SUCCESS(800,"保存成功！"),
    UPDATE_SUCCESS(800,"更新成功"),
    DELETE_SUCCESS(800,"删除成功"),
    OK(800,"调用成功");

    private Integer code;
    private String message;

    private BaseBizEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}