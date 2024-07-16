package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.propagation.serde;

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
 * @author CC11001100
 */
@RestController
public class TrackTaintChainCompletion_JdkSeriable_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * jdk 序列化与反序列化
     *
     * @param sourceTestObject
     * @return
     * @throws ClassNotFoundException
     */
    @PostMapping(value = "case00932")
    @IastTestCase(caseNo = "aTaintCase00932",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->JDK序列化与反序列化",
        thisMethodTag = "aTaintCase00932", hasVul = true)
    public Map<java.lang.String, Object> aTaintCase00932(@RequestBody SourceTestObject sourceTestObject)
        throws ClassNotFoundException {
        Map<java.lang.String, Object> modelMap = new HashMap<>();
        try {
            byte[] bytes = JDKSerializationUtil.serialize(sourceTestObject);
            SourceTestObject testObject = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(testObject.getCmd());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public java.lang.String[] category() {
        return new java.lang.String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点链路完整度,
            //
            Category.LEVEL5_污点传播跟踪能力,
            //
            Category.LEVEL6_传播场景,
            //
            "JDK序列化与反序列化",
            //
        };
    }

    @Override
    public java.lang.String caseNo() {
        return "aTaintCase00932";
    }

    @Override
    public Object requestBodyBean() {
        SourceTestObject testObject = new SourceTestObject();
        testObject.setCmd("ls");
        testObject.setCmd2("cd /");
        return testObject;
    }
}
