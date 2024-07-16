package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.string.stringPartHasTaint.subNotTaint.string.split;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.common.CommonConsts;

/**
 * @author CC11001100
 */
@RestController
public class AccuracyTrackTaintString_Split_003_F implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00942/3")
    public Map<String, Object> aTaintCase00942_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc@";
            String cmdfull = hardcode + cmd;
            String[] data = cmdfull.split("@");
            Runtime.getRuntime().exec(data[0]);
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
        return new AccuracyTrackTaintString_Split_001_T();
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
