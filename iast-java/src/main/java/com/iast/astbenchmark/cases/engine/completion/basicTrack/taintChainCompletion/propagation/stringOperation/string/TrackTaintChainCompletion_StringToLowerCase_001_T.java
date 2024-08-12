package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.propagation.stringOperation.string;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
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
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点链路完整度 -> 污点传播跟踪能力 -> 传播场景 -> String操作 -> toLowerCase
// compose = TrackTaintChainCompletion_StringToLowerCase_001_T.java
// bind_url = /case0077
// assession information end
public class TrackTaintChainCompletion_StringToLowerCase_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase0077 传播场景->String操作->toLowerCase
     */
    @PostMapping(value = "case0077")
    public Map<String, Object> aTaintCase0077(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd = cmd.toLowerCase();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
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
            Category.LEVEL4_污点链路完整度,
            //
            Category.LEVEL5_污点传播跟踪能力,
            //
            Category.LEVEL6_传播场景,
            //
            Category.LEVEL7_String,
            //
            "toLowerCase",
            //
        };
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
