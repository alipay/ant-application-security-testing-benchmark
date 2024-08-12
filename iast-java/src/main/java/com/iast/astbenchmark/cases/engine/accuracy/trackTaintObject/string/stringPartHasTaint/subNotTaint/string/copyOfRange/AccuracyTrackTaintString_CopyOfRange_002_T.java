package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.string.stringPartHasTaint.subNotTaint.string.copyOfRange;

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
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字符串级别 -> 字符串部分存在污点 -> 截取非污点部分 -> char[]/byte[]操作 -> copyOfRange
// compose = AccuracyTrackTaintString_CopyOfRange_002_T.java && AccuracyTrackTaintString_CopyOfRange_001_T.java && !AccuracyTrackTaintString_CopyOfRange_003_F.java
// bind_url = /case00952/2
// assession information end
public class AccuracyTrackTaintString_CopyOfRange_002_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00952/2")
    public Map<String, Object> aTaintCase00952_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            // 被命令执行的字符串全部都是污点
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new AccuracyTrackTaintString_CopyOfRange_001_T();
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
