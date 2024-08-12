package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.string.stringPartHasTaint.subThenConcat.string.replaceAll;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字符串级别 -> 字符串部分存在污点 -> 截取非污点部分后再拼接污点 -> String操作 -> replaceAll
// compose = AccuracyTrackTaintString_StringReplaceAll_002_T.java && AccuracyTrackTaintString_StringReplaceAll_001_T.java
// bind_url = /case00956
// assession information end
public class AccuracyTrackTaintString_StringReplaceAll_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00956")
    @IastTestCase(caseNo = "aTaintCase00956",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->replaceAll",
        thisMethodTag = "aTaintCase00956", hasVul = true)

    public Map<String, Object> aTaintCase00956(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = cmd + hardcode + cmd;
            String data1 = cmdfull.replaceAll(cmd, ""); // 去掉污点
            String dara2 = data1 + cmd; // 拼接污点
            Runtime.getRuntime().exec(dara2);
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
            Category.LEVEL4_字符串级别,
            //
            Category.LEVEL5_字符串部分存在污点,
            //
            "截取非污点部分后再拼接污点",
            //
            "String操作",
            //
            "replaceAll"
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00956";
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
