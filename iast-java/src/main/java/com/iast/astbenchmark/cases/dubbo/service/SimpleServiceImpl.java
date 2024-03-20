package com.iast.astbenchmark.cases.dubbo.service;

// import com.alibaba.dubbo.config.annotation.Service;
//
// @Service
public class SimpleServiceImpl implements ISimpleService {
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
