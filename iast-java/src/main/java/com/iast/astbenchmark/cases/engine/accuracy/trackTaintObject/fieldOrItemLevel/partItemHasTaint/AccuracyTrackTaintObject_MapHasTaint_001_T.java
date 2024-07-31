package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partItemHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分集合元素为污点 -> Map中部分元素为污点
// compose = 
// bind_url = /case00133
// assession information end
public class AccuracyTrackTaintObject_MapHasTaint_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00133 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Map中部分元素为污点
     */
    @PostMapping(value = "case00133")
    @IastTestCase(caseNo = "aTaintCase00133",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Map中部分元素为污点",
        thisMethodTag = "aTaintCase00133", hasVul = true)
    public Map<String, Object> aTaintCase00133(@RequestBody Map<String, String> map) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            // Map<String, String> map = Maps.newHashMap();
            // map.put("1", cmd);
            map.put("2", "ls");
            map.put("3", "cd ~");
            Runtime.getRuntime().exec(map.get("1"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_准确度,
            //
            Category.LEVEL3_污点对象跟踪粒度,
            //
            Category.LEVEL4_字段_元素级别,
            //
            "部分集合元素为污点",
            //
            "Map中部分元素为污点",
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00133";
    }

    @Override
    public Object requestBodyBean() {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "ls");
        return map;
    }

}
