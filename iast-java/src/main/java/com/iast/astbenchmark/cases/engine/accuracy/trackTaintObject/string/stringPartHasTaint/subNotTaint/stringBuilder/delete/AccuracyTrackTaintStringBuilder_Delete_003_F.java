package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.string.stringPartHasTaint.subNotTaint.stringBuilder.delete;

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
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字符串级别 -> 字符串部分存在污点 -> 截取非污点部分 -> StringBuilder操作 -> delete
// compose = AccuracyTrackTaintStringBuilder_Delete_002_T.java && AccuracyTrackTaintStringBuilder_Delete_001_T.java && !AccuracyTrackTaintStringBuilder_Delete_003_F.java
// bind_url = /case00946/3
// assession information end
public class AccuracyTrackTaintStringBuilder_Delete_003_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00946/3")
    public Map<String, Object> aTaintCase00946_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.delete(hardcode.length(), builder.length());
            Runtime.getRuntime().exec(builder.toString());
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
        return new AccuracyTrackTaintStringBuilder_Delete_001_T();
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
