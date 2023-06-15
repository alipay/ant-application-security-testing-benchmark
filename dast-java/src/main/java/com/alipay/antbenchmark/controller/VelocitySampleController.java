package com.alipay.antbenchmark.controller;

import com.alipay.antbenchmark.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class VelocitySampleController {

    @Resource
    private SampleService sampleService;

    @RequestMapping(value = "/velocity", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", sampleService.message());
        return "sample.vm";
    }
}