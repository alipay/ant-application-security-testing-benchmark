package com.iast.astbenchmark.cases.engine.completion.async.threadAsync.crossHttpRequestOnce;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

import cn.hutool.http.HttpRequest;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_CrossHttpRequestOnce_001_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00123 跨进程跟踪能力->调用方式->调用方式->http->单次http调用触发sink
     */
    @PostMapping(value = "case00123")
    public Map<String, Object> aTaintCase00123(@RequestParam String cmd, @RequestParam String auto_check_start_time) {
        Map<String, Object> modelMap = new HashMap<>();
        HttpRequest.post(
            "http://localhost:39100/ataint/case00123/2?cmd=" + cmd + "&auto_check_start_time=" + auto_check_start_time)
            .execute();
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00123";
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
            "跨进程跟踪能力",
            //
            "调用方式",
            //
            "http",
            //
            "跨一层进程调用",
            //
        };
    }

}
