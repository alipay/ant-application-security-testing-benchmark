package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partItemHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分数据元素为污点 -> 多维数组中的部分元素为污点
// compose = 
// bind_url = /case00130
// assession information end
public class AccuracyTrackTaintObject_MultArirayTaint_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00130 污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->多维数组中的部分元素为污点
     */
    @PostMapping(value = "case00130")
    @IastTestCase(caseNo = "aTaintCase00130",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->多维数组中的部分元素为污点",
        thisMethodTag = "aTaintCase00130", hasVul = true)
    public Map<String, Object> aTaintCase00130(@RequestBody String[][] strings) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            // String[][] strings = new String[2][2];
            // strings[0][0] = "ls";
            // strings[0][1] = cmd;
            // strings[1][0] = "cd /";
            // strings[1][1] = "cd /home";
            strings[0][0] = "ls";
            strings[1][0] = "cd /";
            strings[1][1] = "cd /home";
            // [0][1]的内容是外部可控的，所以此处被命令执行的是污点
            Runtime.getRuntime().exec(strings[0][1]);
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
            "多维数组中的部分元素为污点",
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00130";
    }

    @Override
    public Object requestBodyBean() {
        String[][] strings = new String[2][2];
        strings[0][1] = "ls";
        strings[1][1] = "cd /";
        return strings;
    }

}
