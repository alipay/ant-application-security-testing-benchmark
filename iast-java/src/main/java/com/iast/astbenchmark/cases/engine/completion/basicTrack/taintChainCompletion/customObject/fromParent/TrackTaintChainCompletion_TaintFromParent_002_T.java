package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.customObject.fromParent;

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
 * @author CC11001100
 */
@RestController
public class TrackTaintChainCompletion_TaintFromParent_002_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping("case00925/2")
    @IastTestCase(caseNo = "aTaintCase00925",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->污点为父类字段",
        thisMethodTag = "aTaintCase00925_2", hasVul = true)
    public Map<String, Object> aTaintCase00925_2(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda2());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new TrackTaintChainCompletion_TaintFromParent_001_T();
    }

}
