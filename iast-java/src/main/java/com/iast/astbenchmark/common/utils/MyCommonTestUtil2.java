package com.iast.astbenchmark.common.utils;

/**
 * 创建一些测试方法或者工具方法的支撑测试用例
 */
public class MyCommonTestUtil2 {

    public String traceDeepth(String data, int deepth, final int maxDeepth) {
        if (deepth >= maxDeepth) {
            return data;
        }
        for (int i = 0; i < maxDeepth; i++) {
            data = new StringBuilder().append(data).append(i).toString();
        }
        deepth++;
        return traceDeepth(data, deepth, maxDeepth);
    }

    public String traceDeepth(String data, final int maxDeepth) {
        for (int i = 0; i < maxDeepth; i++) {
            data = new StringBuilder().append(data).append(i).toString();
        }
        return data;
    }

}
