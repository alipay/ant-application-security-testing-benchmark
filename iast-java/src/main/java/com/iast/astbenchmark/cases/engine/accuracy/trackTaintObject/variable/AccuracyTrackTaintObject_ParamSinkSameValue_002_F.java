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
 * @author CC11001100
 */
@RestController
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
