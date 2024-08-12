package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partFieldHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.cases.bean.OneLayerSimpleBean;
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
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分字段对象为污点 -> 单层简单对象部分字段为污点
// compose = !AccuracyTrackTaintObject_SingleObjectTaint_002_F.java && AccuracyTrackTaintObject_SingleObjectTaint_001_T.java
// bind_url = /case00126/2
// assession information end
public class AccuracyTrackTaintObject_SingleObjectTaint_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00126/2")
    @IastTestCase(caseNo = "aTaintCase00126",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->单层简单对象部分字段为污点",
        thisMethodTag = "aTaintCase00126_2", hasVul = false)
    public Map<String, Object> aTaintCase00126_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            OneLayerSimpleBean simpleBean = new OneLayerSimpleBean();
            simpleBean.setCmd(cmd);
            simpleBean.setCmd2("cd /");
            // cmd2的内容是常量
            Runtime.getRuntime().exec(simpleBean.getCmd2());
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
        return new AccuracyTrackTaintObject_SingleObjectTaint_001_T();
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
