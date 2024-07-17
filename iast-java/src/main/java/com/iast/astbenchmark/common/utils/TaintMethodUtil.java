package com.iast.astbenchmark.common.utils;

/**
 * 用于模拟污点传播相关的几个方法，此工具还在建设中，后续将修改为能够自己配置
 */
public class TaintMethodUtil {

    /**
     * 用于模拟净化后的数据再次被污染
     *
     * @param data
     * @return
     */
    public static String unSanitizer(String data) {
        return data;
    }

    /**
     *
     * 净化函数，污点经过此方法后被无害化处理
     *
     * @param data
     * @return
     */
    public static String sanitizer(String data) {
        return data;
    }

    /**
     * 危险函数，污点进入此函数后产生危害
     *
     * @param data
     * @return
     */
    public static String sink(String data) {
        return data;
    }

}
