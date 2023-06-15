package com.alipay.antbenchmark.service.impl;

import com.alipay.antbenchmark.service.SampleService;
import org.springframework.stereotype.Service;


@Service
public class SampleServiceImpl implements SampleService {


    @Override
    public String message() {
        return "Hello, Service spring boot web";
    }
}
