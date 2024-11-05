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
import com.iast.astbenchmark.common.utils.JDKSerializationUtil;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分字段对象为污点 -> 部分元素为污点，经过JDK序列化后再反序列化
// compose = AccuracyTrackTaintObject_SerializeDeSerialize_001_T.java && !AccuracyTrackTaintObject_SerializeDeSerialize_002_F.java
// bind_url = /case00131
// assession information end
public class AccuracyTrackTaintObject_SerializeDeSerialize_003_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00131 污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->部分元素为污点，序列化后再反序列化
     */
    @PostMapping(value = "case00131")
    @IastTestCase(caseNo = "aTaintCase00131",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->部分元素为污点，经过JDK序列化后再反序列化",
        thisMethodTag = "aTaintCase00131", hasVul = true)
    public Map<String, Object> aTaintCase00131(@RequestBody String[][] strings) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            strings[0][0] = "ls";
            strings[1][0] = "cd /";
            strings[1][1] = "cd /home";
            byte[] bytes = JDKSerializationUtil.serialize(strings);
            String[][] res = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(res[0][1]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
            Category.LEVEL5_部分字段对象为污点,
            //
            "部分元素为污点，经过JDK序列化后再反序列化",
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00131";
    }

    @Override
    public Object requestBodyBean() {
        String[][] strings = new String[2][2];
        strings[0][1] = "ls";
        strings[1][1] = "cd /";
        return strings;
    }

}
