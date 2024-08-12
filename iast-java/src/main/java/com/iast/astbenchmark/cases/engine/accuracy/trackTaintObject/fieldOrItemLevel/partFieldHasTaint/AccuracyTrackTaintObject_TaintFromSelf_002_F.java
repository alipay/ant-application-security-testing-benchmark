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
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean99;
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
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分字段对象为污点 -> 多层复杂对象部分字段为污点 -> 污点来当前类字段
// compose = AccuracyTrackTaintObject_TaintFromSelf_001_T.java && !AccuracyTrackTaintObject_TaintFromSelf_002_F.java
// bind_url = /case00128/2
// assession information end
public class AccuracyTrackTaintObject_TaintFromSelf_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00128/2")
    @IastTestCase(caseNo = "aTaintCase00128",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来当前类字段",
        thisMethodTag = "aTaintCase00128_2", hasVul = false)
    public Map<String, Object> aTaintCase00128_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            LayerBaseBean99 simpleBean = new LayerBaseBean99();
            simpleBean.setCmda0("cd /");
            simpleBean.setCmdb0("ls -a");
            simpleBean.setCmda99(cmd);
            simpleBean.setCmdb99("ls");
            // 被执行的内容是常量
            Runtime.getRuntime().exec(simpleBean.getCmdb99());;
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
        return new AccuracyTrackTaintObject_TaintFromSelf_001_T();
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
