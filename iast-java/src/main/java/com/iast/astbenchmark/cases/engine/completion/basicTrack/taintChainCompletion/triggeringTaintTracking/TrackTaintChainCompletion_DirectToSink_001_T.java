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
 * @author CC11001100
 */
@RestController
public class TrackTaintChainCompletion_DirectToSink_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * TODO 2024-06-14 16:13:39 不能算没有污点传播吧？毕竟source也不在@RequestParam这里
     *
     * @param cmd
     * @return
     */
    @PostMapping(value = "case00112")
    @IastTestCase(caseNo = "aTaintCase00112",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->触发污点跟踪能力（sink）->无污点传播过程，污点直接传入sink",
        thisMethodTag = "aTaintCase00112", hasVul = true, description = "触发污点跟踪能力（sink）->无污点传播过程，污点直接传入sink")
    public Map<String, Object> aTaintCase00112(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00112";
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
            "无污点传播过程，污点直接传入sink",
            //
        };
    }

}
