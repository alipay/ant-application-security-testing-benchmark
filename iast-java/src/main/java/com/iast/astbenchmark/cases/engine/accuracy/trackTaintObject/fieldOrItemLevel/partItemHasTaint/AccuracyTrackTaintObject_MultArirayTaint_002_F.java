package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partItemHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

/**
 * @author CC11001100
 */
@RestController
public class AccuracyTrackTaintObject_MultArirayTaint_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00130/2")
    @IastTestCase(caseNo = "aTaintCase00130",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->多维数组中的部分元素为污点",
        thisMethodTag = "aTaintCase00130_2", hasVul = false)
    public Map<String, Object> aTaintCase00130_2(@RequestBody String[][] strings) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            strings[0][0] = "ls";
            strings[1][0] = "cd /";
            strings[1][1] = "cd /home";
            Runtime.getRuntime().exec(strings[0][0]);
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
        return new AccuracyTrackTaintObject_MultArirayTaint_001_T();
    }

    @Override
    public Object requestBodyBean() {
        String[][] strings = new String[2][2];
        strings[0][1] = "ls";
        strings[1][1] = "cd /";
        return strings;
    }

}
