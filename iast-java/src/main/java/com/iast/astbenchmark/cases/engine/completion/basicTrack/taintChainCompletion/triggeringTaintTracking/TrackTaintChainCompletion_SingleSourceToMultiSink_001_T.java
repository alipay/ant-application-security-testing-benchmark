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
import com.iast.astbenchmark.common.utils.TaintMethodUtil;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintChainCompletion_SingleSourceToMultiSink_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00109 触发污点跟踪能力（sink）->单污点来源传播至多sink点
     */
    @PostMapping(value = "case00109")
    @IastTestCase(caseNo = "aTaintCase00109",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->触发污点跟踪能力（sink）->单污点来源传播至多sink点",
        thisMethodTag = "aTaintCase00109", hasVul = true)
    public Map<String, Object> aTaintCase00109(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

            // 第一个sink点
            Runtime.getRuntime().exec(cmd);

            cmd = cmd + " -la";
            // 第二个sink点
            TaintMethodUtil.sink(cmd);

            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00109";
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
            "单污点来源传播至多sink点",
            //
        };
    }

}
