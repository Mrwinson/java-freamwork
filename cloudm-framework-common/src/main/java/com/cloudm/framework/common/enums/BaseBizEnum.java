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
    OK(800,"调用成功"),
    YN_Y(BaseBizEnum.FRIST.getCode(), "已删除"),
    YN_N(BaseBizEnum.ZERRO.getCode(), "未删除"),
    DEFAULT_USER(BaseBizEnum.ZERRO.getCode(),"系统管理员"),
    SAVE_SUCCESS(BaseBizEnum.OK.getCode(),"保存成功！"),
    UPDATE_SUCCESS(BaseBizEnum.OK.getCode(),"更新成功"),
    DELETE_SUCCESS(BaseBizEnum.OK.getCode(),"删除成功");


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