package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.variable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction sink点的值非外部可控，但与某个参数值相同 这个case期望不能被检出污点
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 变量级别 -> sink点的值非外部可控，但与某个参数值相同
// compose = !AccuracyTrackTaintObject_ParamSinkSameValue_001_F.java && !AccuracyTrackTaintObject_ParamSinkSameValue_002_F.java
// bind_url = /case00125/2
// assession information end
public class AccuracyTrackTaintObject_ParamSinkSameValue_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00125/2")
    public Map<String, Object> aTaintCase00125_2(@RequestParam String cmd1,
        @RequestParam(defaultValue = "") String cmd2) {

        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = "";
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }

        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new AccuracyTrackTaintObject_ParamSinkSameValue_001_F();
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("cmd1", "cd /");
        paramMap.put("cmd2", "ls");
        return paramMap;
    }

}
