package com.iast.astbenchmark.cases.engine.completion.async.threadAsync.crossHttpRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
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
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 异步跟踪能力 -> 跨进程跟踪能力 -> 调用方式 -> http -> 跨多层进程调用
// compose = !TrackTaintObjectCompletion_CrossHttpRequest_002_F.java && TrackTaintObjectCompletion_CrossHttpRequest_003_T.java && !TrackTaintObjectCompletion_CrossHttpRequest_001_F.java
// bind_url = /case00124/3
// assession information end
public class TrackTaintObjectCompletion_CrossHttpRequest_003_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00124/3")
    @IastTestCase(caseNo = "aTaintCase00124", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->跨进程跟踪能力->调用方式->http->跨多层进程调用",
        thisMethodTag = "aTaintCase00124_3", hasVul = true)
    public Map<String, Object> aTaintCase00124_3(@RequestParam String cmd, @RequestParam String auto_check_start_time) {
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
    public Boolean hasVul() {
        return true;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new TrackTaintObjectCompletion_CrossHttpRequest_001_F();
    }

}
