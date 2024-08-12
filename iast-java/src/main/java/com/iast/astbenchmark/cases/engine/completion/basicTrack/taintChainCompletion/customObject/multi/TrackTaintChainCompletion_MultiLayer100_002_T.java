package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.customObject.multi;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.cases.bean.SourceTestWith100Filedsbject;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 自定义对象 -> 对象字段 -> 多层字段 -> 100层
// compose = TrackTaintChainCompletion_MultiLayer100_003_T.java && TrackTaintChainCompletion_MultiLayer100_001_T.java && TrackTaintChainCompletion_MultiLayer100_002_T.java
// bind_url = /case00922/2
// assession information end
public class TrackTaintChainCompletion_MultiLayer100_002_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping("case00922/2")
    @IastTestCase(caseNo = "aTaintCase00922",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->100",
        thisMethodTag = "aTaintCase00922_2", hasVul = true)
    public Map<String, Object> aTaintCase00922_2(@RequestBody SourceTestWith100Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd50());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new TrackTaintChainCompletion_MultiLayer100_001_T();
    }

}
