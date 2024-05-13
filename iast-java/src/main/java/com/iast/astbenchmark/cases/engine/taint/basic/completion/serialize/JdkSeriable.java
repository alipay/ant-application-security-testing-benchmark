package com.iast.astbenchmark.cases.engine.taint.basic.completion.serialize;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.cases.bean.SourceTestObject;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.JDKSerializationUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class JdkSeriable {

    /**
     * jdk 序列化与反序列化
     *
     * @param sourceTestObject
     * @return
     * @throws ClassNotFoundException
     */
    @PostMapping(value = "case00932")
    @IastTestCase(
            caseNo ="aTaintCase00932",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->JDK序列化与反序列化",
            thisMethodTag = "aTaintCase00932",
            hasVul = true
    )
    public Map<String, Object> aTaintCase00932(@RequestBody SourceTestObject sourceTestObject) throws ClassNotFoundException {
        Map<String, Object> modelMap = new HashMap<>();
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

}
