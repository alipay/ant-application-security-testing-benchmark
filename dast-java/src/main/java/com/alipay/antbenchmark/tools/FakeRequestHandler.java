package com.alipay.antbenchmark.tools;

import javax.servlet.http.HttpServletRequest;

public class FakeRequestHandler {
    private final HttpServletRequest httpServletRequest;


    public FakeRequestHandler(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    // 获取一个固定值
    public String getFakeValue(String p) {
        return "bar";
    }
}