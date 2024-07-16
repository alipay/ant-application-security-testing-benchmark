package com.iast.astbenchmark.cases.engine.completion.async.storage.localCache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_SameJvmCache_001_F implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00115 异步跟踪能力->存储行异步->污点通过缓存存储后触发->本地缓存
     */
    public static Cache<String, String> cache = CacheUtil.newLFUCache(5, 6000 * 30);

    /**
     * 本次缓存是跨线程传播的一种特殊情况
     *
     * @param cmd
     * @return
     */
    @PostMapping(value = "case00115")
    public Map<String, Object> aTaintCase00115(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        cache.put("cacheKey", cmd);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public String description() {
        return "污点保存到当前进程的内存缓存，后续读取将其作为命令执行";
    }

    @Override
    public String caseNo() {
        return "aTaintCase00115";
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
            "本地缓存",
            //
        };
    }

}
