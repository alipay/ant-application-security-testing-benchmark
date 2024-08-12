package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.queue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
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
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字段/元素级别 -> 部分集合元素为污点 -> 集合中部分元素为污点，经过JDK序列化后再反序列化
// compose = AccuracyTrackTaintObject_QueuePartTaintJdkSerDe_001_T.java && !AccuracyTrackTaintObject_QueuePartTaintJdkSerDe_002_F.java
// bind_url = /case00136
// assession information end
public class AccuracyTrackTaintObject_QueuePartTaintJdkSerDe_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00136 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->集合中部分元素为污点，序列化后再反序列化
     *
     * TODO 继续补充一些其它集合类型的例子
     */
    @PostMapping(value = "case00136")
    @IastTestCase(caseNo = "aTaintCase00136",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->集合中部分元素为污点，经过JDK序列化后再反序列化",
        thisMethodTag = "aTaintCase00136", hasVul = true)
    public Map<String, Object> aTaintCase00136(@RequestBody List<String> list) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            // List<String> list = Lists.newArrayList();
            // list.add(cmd);
            list.add("cd /");
            list.add("cd ~");
            byte[] bytes = JDKSerializationUtil.serialize(list);
            List<String> strings = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(strings.get(0));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00136";
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
            "部分集合元素为污点",
            //
            "集合中部分元素为污点，经过JDK序列化后再反序列化",
            //
        };
    }

    @Override
    public Object requestBodyBean() {
        List<String> list = Lists.newArrayList();
        list.add("ls");
        return list;
    }

}
