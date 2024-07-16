package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partItemHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分数据元素为污点 -> Set中部分元素为污点
// compose = 
// bind_url = /case00134/2
// assession information end
public class AccuracyTrackTaintObject_SetHasTaint_002_F implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00134/2")
    @IastTestCase(caseNo = "aTaintCase00134",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Set中部分元素为污点",
        thisMethodTag = "aTaintCase00134", hasVul = false)
    public Map<String, Object> aTaintCase00134_2(@RequestBody SoureWithSetBean setBean) {
        Map<String, Object> modelMap = new HashMap<>();
        Set<String> set = setBean.getValue();
        set.add("cd /");
        set.add("cd ~");
        set.stream().forEach(e -> {
            try {
                if ("cd /".equals(e)) {
                    Runtime.getRuntime().exec(e);
                }
            } catch (IOException ex) {
            }
        });

        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new AccuracyTrackTaintObject_SetHasTaint_001_T();
    }

    @Override
    public Object requestBodyBean() {
        Set<String> set = new HashSet<>();
        set.add("ls");
        SoureWithSetBean setBean = new SoureWithSetBean();
        setBean.setKey("key");
        setBean.setValue(set);
        return setBean;
    }

}
