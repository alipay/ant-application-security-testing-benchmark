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
* Introduction 污点直接赋值为硬编码值 (硬编码是一种sanitizer方式)
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 污点无害化处理能力(sanitizer) -> 污点直接赋值为硬编码值
// compose = TrackTaintObjectCompletion_HardCode_T_001.java && !TrackTaintObjectCompletion_HardCode_F_002.java
// bind_url = /case00141
// assession information end
public class TrackTaintObjectCompletion_HardCode_F_002 implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00141")
    public Map<String, Object> aTaintCase00141(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        cmd = "test";
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
    public Boolean hasVul() {
        return false;
    }

    @Override
    public String description() {
        return "污点直接赋值为硬编码值 (硬编码是一种sanitizer方式)";
    }

    @Override
    public String requestBodyString() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_STRING_BODY;
    }

}
