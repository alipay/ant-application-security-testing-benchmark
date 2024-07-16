package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.collection;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_Map_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping("case005")
    public Map<String, Object> aTaintCase005(@RequestBody Map<String, String> cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.isEmpty()) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }

        try {
            Runtime.getRuntime().exec(cmd.get(cmd.keySet().iterator().next()));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }

        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase005";
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点对象完整度,
            //
            Category.LEVEL5_集合_集合对象全为污点,
            //
            "Map元素",
            //
        };
    }

    @Override
    public Object requestBodyBean() {
        Map<String, String> map = Maps.newHashMap();
        map.put("cmd", "ls");
        return map;
    }

}
