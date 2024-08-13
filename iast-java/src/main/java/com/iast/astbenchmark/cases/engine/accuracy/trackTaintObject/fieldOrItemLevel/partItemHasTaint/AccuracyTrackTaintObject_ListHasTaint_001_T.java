package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partItemHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
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
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分集合元素为污点 -> List中部分元素为污点
// compose = AccuracyTrackTaintObject_ListHasTaint_001_T.java && !AccuracyTrackTaintObject_ListHasTaint_002_F.java
// bind_url = /case00132
// assession information end
public class AccuracyTrackTaintObject_ListHasTaint_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00132 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->List中部分元素为污点
     */
    @PostMapping(value = "case00132")
    @IastTestCase(caseNo = "aTaintCase00132",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->List中部分元素为污点",
        thisMethodTag = "aTaintCase00132", hasVul = true)
    public Map<String, Object> aTaintCase00132(@RequestBody List<String> stringList) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            stringList.add("cd /");
            stringList.add("cd ~");
            Runtime.getRuntime().exec(stringList.get(0));
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
            "List中部分元素为污点",
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00132";
    }

    @Override
    public Object requestBodyBean() {
        List<String> stringList = Lists.newArrayList();
        stringList.add("ls");
        return stringList;
    }

}
