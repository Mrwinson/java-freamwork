package com.cloudm.framework.db.datasource;

/**
 * @description: 数据库上下文持有者（工具类）
 * @author: Courser
 * @date: 2017/5/10
 * @version: V1.0
 */
public class DataSourceContextHolder {

    /**
     * 本地安全线程
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 设置 数据库datasource;
     * @param dbName
     */
    public static void setDbName(String dbName) {
        contextHolder.set(dbName);
    }

    /**
     * 获取 数据源名称
     * @return
     */
    public static String getDbName() {
        return ((String) contextHolder.get());
    }

    /**
     * 清除数据源
     */
    public static void clearDbName() {
        contextHolder.remove();
    }
}