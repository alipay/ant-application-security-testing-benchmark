package com.iast.astbenchmark.cases.engine.completion.async.storage.sessionStorage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.SessionUtil;

/**
* Introduction 通过Session存储污点，后续取出污点后将其命令执行
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 异步跟踪能力 -> 存储型异步 -> 污点通过session存储后触发
// compose = 
// bind_url = /case00114/1
// assession information end
public class TrackTaintObjectCompletion_SessionStorage_002_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00114/1")
    @IastTestCase(caseNo = "aTaintCase00114", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过session存储后触发",
        thisMethodTag = "aTaintCase00114_1", hasVul = true)
    public Map<String, Object> aTaintCase00114_1(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = SessionUtil.getSession(request, "sessionKey");
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new TrackTaintObjectCompletion_SessionStorage_001_F();
    }

}
