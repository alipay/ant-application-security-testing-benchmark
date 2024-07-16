package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.customObject.fromParent;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 自定义对象 -> 对象字段 -> 污点为父类字段
// compose = 
// bind_url = /case00925
// assession information end
public class TrackTaintChainCompletion_TaintFromParent_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping("case00925")
    @IastTestCase(
            caseNo ="aTaintCase00925",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->污点为父类字段",
            thisMethodTag = "aTaintCase00925",
            hasVul = true
    )
    public Map<String, Object> aTaintCase00925(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda0());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00925";
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
                "自定义对象",
                //
                "对象字段",
                //
                "污点为父类字段",
                //
        };
    }

}
