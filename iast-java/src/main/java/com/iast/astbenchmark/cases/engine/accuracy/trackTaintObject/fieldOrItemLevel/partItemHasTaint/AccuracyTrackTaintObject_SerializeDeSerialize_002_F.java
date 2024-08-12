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
import com.iast.astbenchmark.common.utils.JDKSerializationUtil;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分字段对象为污点 -> 部分元素为污点，经过JDK序列化后再反序列化
// compose = AccuracyTrackTaintObject_SerializeDeSerialize_001_T.java && !AccuracyTrackTaintObject_SerializeDeSerialize_002_F.java
// bind_url = /case00131/2
// assession information end
public class AccuracyTrackTaintObject_SerializeDeSerialize_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00131/2")
    @IastTestCase(caseNo = "aTaintCase00131",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->部分元素为污点，经过JDK序列化后再反序列化",
        thisMethodTag = "aTaintCase00131_2", hasVul = false)
    public Map<String, Object> aTaintCase00131_2(@RequestBody String[][] strings) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            strings[0][0] = "ls";
            strings[1][0] = "cd /";
            strings[1][1] = "cd /home";
            byte[] bytes = JDKSerializationUtil.serialize(strings);
            String[][] res = JDKSerializationUtil.deSerialize(bytes);
            // 因为上面的赋值相当于内容固定了，所以这里指定的命令可以认为是一个常量，那这时应不应该报漏洞呢？
            Runtime.getRuntime().exec(res[0][0]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new AccuracyTrackTaintObject_SerializeDeSerialize_001_T();
    }

    @Override
    public Object requestBodyBean() {
        String[][] strings = new String[2][2];
        strings[0][1] = "ls";
        strings[1][1] = "cd /";
        return strings;
    }

}
