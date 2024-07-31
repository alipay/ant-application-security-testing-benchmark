package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.sanitizer.sanitizerSupportType;

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
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 基础跟踪能力 -> 污点链路完整度 -> 污点无害化处理能力(sanitizer) -> sanitizer支持区分类型
// compose = 
// bind_url = /case00103/1
// assession information end
public class TrackTaintChainCompletion_SanitizerSupportType_002_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00103/1")
    @IastTestCase(caseNo = "aTaintCase00103",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点无害化处理能力(sanitizer)->sanitizer支持区分类型",
        thisMethodTag = "aTaintCase00103_1", hasVul = true)
    public Map<String, Object> aTaintCase00103_1(@RequestParam String cmd) {
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
            Category.LEVEL2_准确度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点链路完整度,
            //
            Category.LEVEL5_污点无害化处理能力,
            //
            "sanitizer支持区分类型",
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
