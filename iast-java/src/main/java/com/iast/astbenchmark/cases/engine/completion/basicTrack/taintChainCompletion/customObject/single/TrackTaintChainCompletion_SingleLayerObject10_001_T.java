package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.customObject.single;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.cases.bean.SourceTestWith10Filedsbject;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 自定义对象 -> 对象字段 -> 单层字段 -> 10
// compose = TrackTaintChainCompletion_SingleLayerObject10_003_T.java && TrackTaintChainCompletion_SingleLayerObject10_001_T.java && TrackTaintChainCompletion_SingleLayerObject10_002_T.java
// bind_url = /case00921
// assession information end
public class TrackTaintChainCompletion_SingleLayerObject10_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 对象字段->单层字段->10@aTaintCase00921
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00921")
    @IastTestCase(caseNo = "aTaintCase00921",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->10",
        thisMethodTag = "aTaintCase00921", hasVul = true)
    public Map<String, Object> aTaintCase00921(@RequestBody SourceTestWith10Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00921";
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
            "单层字段",
            //
            "10",
            //
        };
    }

}
