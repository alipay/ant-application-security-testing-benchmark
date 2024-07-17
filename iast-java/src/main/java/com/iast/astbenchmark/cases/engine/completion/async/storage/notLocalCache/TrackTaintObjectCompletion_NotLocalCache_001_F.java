package com.iast.astbenchmark.cases.engine.completion.async.storage.notLocalCache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction 污点通过Redis的setKey保存到了Redis上，后续getKey读取到了污点进行命令执行
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 异步跟踪能力 -> 存储型异步 -> 污点通过缓存存储后触发 -> 非本地缓存
// compose = 
// bind_url = /case00138
// assession information end
public class TrackTaintObjectCompletion_NotLocalCache_001_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00138 异步跟踪能力->存储型异步->污点通过缓存存储后触发->非本地缓存
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping(value = "case00138")
    public Map<String, Object> aTaintCase00138(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String key = "cmd_key";
        redisTemplate.opsForValue().set(key, cmd);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00138";
    }

    @Override
    public String description() {
        return "污点通过Redis的setKey保存到了Redis上，后续getKey读取到了污点进行命令执行";
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
            "存储型异步",
            //
            "污点通过缓存存储后触发",
            //
            "非本地缓存",
            //
        };
    }

}
