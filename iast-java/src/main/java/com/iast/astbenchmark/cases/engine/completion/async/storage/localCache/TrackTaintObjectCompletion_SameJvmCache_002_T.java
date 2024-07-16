package com.iast.astbenchmark.cases.engine.completion.async.storage.localCache;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* Introduction 污点保存到当前进程的内存缓存，后续读取将其作为命令执行
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 异步跟踪能力 -> 存储型异步 -> 污点通过缓存存储后触发 -> 本地缓存
// compose = 
// bind_url = /case00115/1
// assession information end
public class TrackTaintObjectCompletion_SameJvmCache_002_T
        implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00115/1")
    @IastTestCase(caseNo = "aTaintCase00115",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过缓存存储后触发->本地缓存", thisMethodTag = "aTaintCase00115_1",
            hasVul = true)
    public Map<String, Object> aTaintCase00115_1() {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(TrackTaintObjectCompletion_SameJvmCache_001_F.cache.get("cacheKey"));
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
        return new TrackTaintObjectCompletion_SameJvmCache_001_F();
    }

}
