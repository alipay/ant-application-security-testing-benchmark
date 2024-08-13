package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.sanitizer.triggerSinkThenSanitizer;

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
import com.iast.astbenchmark.common.utils.TaintMethodUtil;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 基础跟踪能力 -> 污点链路完整度 -> 污点无害化处理能力(sanitizer) -> 触发sink后再执行sanitizer
// compose = TrackTaintChainCompletion_UnSanitizerThenSanitizer_001_T.java && TrackTaintChainCompletion_UnSanitizerThenSanitizer_003_T.java && !TrackTaintChainCompletion_UnSanitizerThenSanitizer_002_F.java
// bind_url = /case00104/1
// assession information end
public class TrackTaintChainCompletion_UnSanitizerThenSanitizer_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00104/1")
    @IastTestCase(caseNo = "aTaintCase00104",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点无害化处理能力(sanitizer)->触发sink后再执行sanitizer",
        thisMethodTag = "aTaintCase00104_1", hasVul = false)
    public Map<String, Object> aTaintCase00104_1(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String res = TaintMethodUtil.sanitizer(cmd);
        TaintMethodUtil.sink(res);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_准确度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点链路完整度,
            //
            Category.LEVEL5_污点无害化处理能力,
            //
            "触发sink后再执行sanitizer",
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return null;
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }
}
