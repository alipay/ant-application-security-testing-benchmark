package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.queue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.JDKSerializationUtil;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分集合元素为污点 -> 集合中部分元素为污点，经过JDK序列化后再反序列化
// compose = 
// bind_url = /case00136/2
// assession information end
public class AccuracyTrackTaintObject_QueuePartTaintJdkSerDe_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00136/2")
    @IastTestCase(caseNo = "aTaintCase00136",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->集合中部分元素为污点，经过JDK序列化后再反序列化",
        thisMethodTag = "aTaintCase00136", hasVul = false)
    public Map<String, Object> aTaintCase00136_2(@RequestBody List<String> list) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            // List<String> list = Lists.newArrayList();
            // list.add(cmd);
            list.add("ls");
            list.add("cd ~");
            byte[] bytes = JDKSerializationUtil.serialize(list);
            List<String> strings = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(strings.get(1));
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
    public String caseNo() {
        return "aTaintCase00136";
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new AccuracyTrackTaintObject_QueuePartTaintJdkSerDe_001_T();
    }

    @Override
    public Object requestBodyBean() {
        List<String> list = Lists.newArrayList();
        list.add("ls");
        return list;
    }

}
