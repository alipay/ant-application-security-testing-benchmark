package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.propagation.constructor.string;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点链路完整度 -> 污点传播跟踪能力 -> 传播场景 -> String操作 -> 构造方法 -> String(byte bytes[], int offset, int length, String charsetName)
// compose = TrackTaintChainCompletion_StringConstructorByteArray_001_T.java
// bind_url = /case00146
// assession information end
public class TrackTaintChainCompletion_StringConstructorByteArray_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00146")
    @IastTestCase(caseNo = "aTaintCase00146",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->String操作->构造方法->String(byte bytes[], int offset, int length, String charsetName)",
        thisMethodTag = "aTaintCase00146", hasVul = true)
    public Map<java.lang.String, Object> aTaintCase00146(@RequestParam java.lang.String cmd) {
        Map<java.lang.String, Object> modelMap = new HashMap<>();
        try {
            byte[] bytes = cmd.getBytes();
            Runtime.getRuntime().exec(new java.lang.String(bytes, 0, bytes.length, "utf-8"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public java.lang.String[] category() {
        return new java.lang.String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点链路完整度,
            //
            Category.LEVEL5_污点传播跟踪能力,
            //
            Category.LEVEL6_传播场景,
            //
            Category.LEVEL7_String,
            //
            "构造方法",
            //
            "String(byte bytes[], int offset, int length, String charsetName)",
            //
        };
    }

    @Override
    public java.lang.String caseNo() {
        return "aTaintCase00146";
    }

    @Override
    public Map<java.lang.String, java.lang.String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }
}
