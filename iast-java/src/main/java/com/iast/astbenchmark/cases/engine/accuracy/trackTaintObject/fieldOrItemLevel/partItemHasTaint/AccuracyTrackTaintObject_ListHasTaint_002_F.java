package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partItemHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
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
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分集合元素为污点 -> List中部分元素为污点
// compose = AccuracyTrackTaintObject_ListHasTaint_001_T.java && !AccuracyTrackTaintObject_ListHasTaint_002_F.java
// bind_url = /case00132/2
// assession information end
public class AccuracyTrackTaintObject_ListHasTaint_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00132/2")
    @IastTestCase(caseNo = "aTaintCase00132",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->List中部分元素为污点",
        thisMethodTag = "aTaintCase00132_2", hasVul = false)
    public Map<String, Object> aTaintCase00132_2(@RequestBody List<String> stringList) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            stringList.add("ls");
            stringList.add("cd ~");
            // TODO 2024-06-11 21:00:49 这个取值不严谨吧？index 1可能是http请求参数传递进来的也可能不是，主要取决于测试请求的payload
            Runtime.getRuntime().exec(stringList.get(1));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new AccuracyTrackTaintObject_ListHasTaint_001_T();
    }

    @Override
    public Object requestBodyBean() {
        List<String> stringList = Lists.newArrayList();
        stringList.add("ls");
        return stringList;
    }

}
