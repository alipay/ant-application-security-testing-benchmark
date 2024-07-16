package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partItemHasTaint;

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
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction 污点对象被赋值给单维数组的某个下标，然后再通过数组数组下标访问，看是否能够追踪得到
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分数据元素为污点 -> 单维数组中的部分元素为污点
// compose = 
// bind_url = /case00129
// assession information end
public class AccuracyTrackTaintObject_SingleArrayTaint_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00129 污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->单维数组中的部分元素为污点
     */
    @PostMapping(value = "case00129")
    @IastTestCase(caseNo = "aTaintCase00129",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->单维数组中的部分元素为污点",
        thisMethodTag = "aTaintCase00129", hasVul = true)

    public Map<String, Object> aTaintCase00129(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] strings = new String[3];
            strings[0] = "cd ~";
            strings[1] = cmd;
            strings[2] = "cd /";
            // 污点变量在此处被当做命令执行
            Runtime.getRuntime().exec(strings[1]);
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
            "部分数据元素为污点",
            //
            "单维数组中的部分元素为污点",
            //
        };
    }

    @Override
    public String description() {
        return "污点对象被赋值给单维数组的某个下标，然后再通过数组数组下标访问，看是否能够追踪得到";
    }

    @Override
    public String caseNo() {
        return "aTaintCase00129";
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
