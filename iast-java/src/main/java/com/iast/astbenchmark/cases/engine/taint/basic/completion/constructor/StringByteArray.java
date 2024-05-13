package com.iast.astbenchmark.cases.engine.taint.basic.completion.constructor;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class StringByteArray {

    @PostMapping(value = "case00146")
    @IastTestCase(
            caseNo ="aTaintCase00146",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->String操作->构造方法->String(byte bytes[], int offset, int length, String charsetName)",
            thisMethodTag = "aTaintCase00146",
            hasVul = true
    )
    public Map<java.lang.String, Object> aTaintCase00146(@RequestParam java.lang.String cmd) {
        Map<java.lang.String, Object> modelMap = new HashMap<>();
        try {
            byte[] bytes = cmd.getBytes();
            Runtime.getRuntime().exec(new java.lang.String(bytes, 0, bytes.length, "utf-8"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
