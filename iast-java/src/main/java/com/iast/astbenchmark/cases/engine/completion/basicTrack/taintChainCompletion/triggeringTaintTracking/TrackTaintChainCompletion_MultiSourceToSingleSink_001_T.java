package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.triggeringTaintTracking;

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
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点链路完整度 -> 触发污点跟踪能力（sink） -> 多污点来源传播至单sink点
// compose = 
// bind_url = /case00110
// assession information end
public class TrackTaintChainCompletion_MultiSourceToSingleSink_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00110")
    @IastTestCase(caseNo = "aTaintCase00110",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->触发污点跟踪能力（sink）->多污点来源传播至单sink点",
        thisMethodTag = "aTaintCase00110", hasVul = true, description = "触发污点跟踪能力（sink）->多污点来源传播至单sink点")
    public Map<String, Object> aTaintCase00110(@RequestParam String cmd1, @RequestParam String cmd2) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd1 + " -" + cmd2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00110";
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
            "触发污点跟踪能力（sink）",
            //
            "多污点来源传播至单sink点",
            //
        };
    }

}
