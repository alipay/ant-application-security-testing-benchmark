package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partFieldHasTaint;

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
import com.iast.astbenchmark.cases.bean.SourceTestObject;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.JDKSerializationUtil;

/**
* Introduction 对象部分字段为污点，经过JDK序列化后再反序列化
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分字段对象为污点 -> 对象部分字段为污点，经过JDK序列化后再反序列化
// compose = 
// bind_url = /case00142
// assession information end
public class AccuracyTrackTaintObject_SerializeDeSerialize_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00142")
    @IastTestCase(caseNo = "aTaintCase00142",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->对象部分字段为污点，经过JDK序列化后再反序列化",
        thisMethodTag = "aTaintCase00142", hasVul = true)
    public Map<String, Object> aTaintCase00142(@RequestBody SourceTestObject testObject) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            // TODO 2024-06-25 14:19:24 看起来并不是只有一部分字段是污点，而是整个对象都是污点，这个描述是不是不太准确？
            // String str = JSON.toJSONString(testObject);
            // SourceTestObject strings1 = JSON.parseObject(str, SourceTestObject.class);
            byte[] bytes = JDKSerializationUtil.serialize(testObject);
            SourceTestObject object = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(object.getCmd());
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
            "对象部分字段为污点，经过JDK序列化后再反序列化",
            //
        };
    }

    @Override
    public String description() {
        return "对象部分字段为污点，经过JDK序列化后再反序列化";
    }

    @Override
    public String caseNo() {
        return "aTaintCase00142";
    }

    @Override
    public Object requestBodyBean() {
        SourceTestObject testObject = new SourceTestObject();
        testObject.setCmd("ls");
        testObject.setCmd2("cd /");
        return testObject;
    }

}
