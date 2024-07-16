package com.iast.astbenchmark.cases.engine.completion.async.storage.notLocalCache;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_NotLocalCache_002_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00138 异步跟踪能力->存储行异步->污点通过缓存存储后触发->非本地缓存
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping(value = "case00138/1")
    @IastTestCase(caseNo = "aTaintCase00138",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过缓存存储后触发->非本地缓存",
        thisMethodTag = "aTaintCase00138_1", hasVul = true)
    public Map<String, Object> aTaintCase00138_1() {
        Map<String, Object> modelMap = new HashMap<>();
        String key = "cmd_key";
        try {
            String exec = (String)redisTemplate.opsForValue().get(key);
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } finally {
            redisTemplate.delete(key);
        }
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return true;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new TrackTaintObjectCompletion_NotLocalCache_001_F();
    }

}
