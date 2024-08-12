package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partItemHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;
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
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分数据元素为污点 -> Set中部分元素为污点
// compose = AccuracyTrackTaintObject_SetHasTaint_001_T.java && !AccuracyTrackTaintObject_SetHasTaint_002_F.java
// bind_url = /case00134
// assession information end
public class AccuracyTrackTaintObject_SetHasTaint_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00134 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Set中部分元素为污点
     */
    @PostMapping(value = "case00134")
    @IastTestCase(caseNo = "aTaintCase00134",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Set中部分元素为污点",
        thisMethodTag = "aTaintCase00134", hasVul = true)
    public Map<String, Object> aTaintCase00134(@RequestBody SoureWithSetBean setBean) {
        Map<String, Object> modelMap = new HashMap<>();
        Set<String> set = setBean.getValue();
        set.add("cd /");
        set.add("cd ~");
        set.stream().forEach(e -> {
            try {
                // TODO 2024-06-26 16:27:39 如果是这里写死的话，那到底还应不应该报漏洞呢？这是个值得讨论的问题
                if ("ls".equals(e)) {
                    Runtime.getRuntime().exec(e);
                }
            } catch (IOException ex) {
            }
        });

        modelMap.put("status", CommonConsts.SUCCESS_STR);
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
            "Set中部分元素为污点",
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00134";
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
