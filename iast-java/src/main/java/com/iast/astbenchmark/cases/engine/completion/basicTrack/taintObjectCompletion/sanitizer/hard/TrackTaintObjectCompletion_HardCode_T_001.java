package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.sanitizer.hard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.TaintMethodUtil;
import org.springframework.web.bind.annotation.RestController;

/**
* Introduction 污点直接赋值为硬编码值
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 污点无害化处理能力(sanitizer) -> 污点直接赋值为硬编码值
// compose = 
// bind_url = /case00141/1
// assession information end
public class TrackTaintObjectCompletion_HardCode_T_001 implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00141/1")
    public Map<String, Object> aTaintCase00141_1(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        TaintMethodUtil.sink(cmd);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点对象完整度,
            //
            Category.LEVEL5_污点无害化处理能力,
            //
            Category.LEVEL6_污点直接赋值为硬编码值,
            //
        };
    }

    @Override
    public String description() {
        return "污点直接赋值为硬编码值";
    }

    @Override
    public String requestBodyString() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_STRING_BODY;
    }

}
