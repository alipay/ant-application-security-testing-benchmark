package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.string.stringPartHasTaint.subNotTaint.stringBuilder.replace;

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
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字符串级别 -> 字符串部分存在污点 -> 截取非污点部分 -> StringBuilder操作 -> replace
// compose = 
// bind_url = /case00949/2
// assession information end
public class AccuracyTrackTaintStringBuilder_Replace_002_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00949/2")
    public Map<String, Object> aTaintCase00949_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new AccuracyTrackTaintStringBuilder_Replace_001_F();
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
