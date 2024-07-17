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
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean2;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 自定义对象 -> 对象字段 -> 多层字段 -> 3层
// compose = 
// bind_url = /case00923
// assession information end
public class TrackTaintChainCompletion_MultiLayer3_003_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping("case00923")
    @IastTestCase(caseNo = "aTaintCase00923",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->多层字段->3层",
        thisMethodTag = "aTaintCase00923", hasVul = true)
    public Map<String, Object> aTaintCase00923(@RequestBody LayerBaseBean2 cmd) {
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
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new TrackTaintChainCompletion_MultiLayer3_001_T();
    }

}
