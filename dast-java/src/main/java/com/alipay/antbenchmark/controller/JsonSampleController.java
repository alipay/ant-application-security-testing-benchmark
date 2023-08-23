package com.alipay.antbenchmark.controller;

import com.alipay.antbenchmark.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class JsonSampleController {

    @Resource
    private SampleService sampleService;

    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public User printWelcome() {
        return new User("zhangsan", 21, sampleService.message());
    }
}
