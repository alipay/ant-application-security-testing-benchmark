package com.iast.astbenchmark.cases.engine.completion.async.threadAsync.crossThread;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
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
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 异步跟踪能力 -> 多线程异步 -> 污点的来源和触发在不同线程
// compose = TrackTaintObjectCompletion_CrossThread_001_T.java
// bind_url = /case00119
// assession information end
public class TrackTaintObjectCompletion_CrossThread_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00118 异步跟踪能力->存储行异步-> 支持自定义污点的存储和再次提取点
     */
    /**
     * aTaintCase00119 异步跟踪能力->多线程异步->污点的来源和触发在不同线程
     */
    @PostMapping(value = "case00119")
    @IastTestCase(caseNo = "aTaintCase00119", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->多线程异步->污点的来源和触发在不同线程",
        thisMethodTag = "aTaintCase00119", hasVul = true)
    public Map<String, Object> aTaintCase00119(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Thread thread = new Thread(() -> {
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
            }
        });
        String name = thread.getName();
        thread.start();
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_异步跟踪能力,
            //
            "多线程异步",
            //
            "污点的来源和触发在不同线程",
            //
        };
    }

}
