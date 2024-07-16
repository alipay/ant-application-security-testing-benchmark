package com.iast.astbenchmark.cases.engine.completion.async.threadAsync.crossHttpRequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

import cn.hutool.http.HttpRequest;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_CrossHttpRequest_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00124/2")
    public Map<String, Object> aTaintCase00124_2(@RequestParam String cmd, @RequestParam String auto_check_start_time) {
        Map<String, Object> modelMap = new HashMap<>();
        // TODO 2024-06-18 11:44:25 这里的地址和端口不应该写死
        HttpRequest.post(
            "http://localhost:39100/ataint/case00124/3?cmd=" + cmd + "&auto_check_start_time=" + auto_check_start_time)
            .execute();
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new TrackTaintObjectCompletion_CrossHttpRequest_001_F();
    }

}
