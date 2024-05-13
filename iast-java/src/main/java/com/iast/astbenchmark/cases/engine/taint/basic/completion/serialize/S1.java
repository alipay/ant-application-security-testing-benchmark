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
public class S1 {

    @PostMapping(value = "case00142")
    @IastTestCase(
            caseNo = "aTaintCase00142",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->对象部分字段为污点，经过JDK序列化后再反序列化",
            thisMethodTag = "aTaintCase00142",
            hasVul = true
    )
    public Map<String, Object> aTaintCase00142(@RequestBody SourceTestObject testObject) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //String str = JSON.toJSONString(testObject);
            //SourceTestObject strings1 = JSON.parseObject(str, SourceTestObject.class);
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

}
