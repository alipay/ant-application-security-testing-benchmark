package com.iast.astbenchmark.cases.engine.taint.basic.completion.constructor;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class StringCharset {

    @PostMapping(value = "case00147")
    @IastTestCase(
            caseNo ="aTaintCase00147",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->String操作->构造方法->String(byte bytes[], int offset, int length, Charset charset)",
            thisMethodTag = "aTaintCase00147",
            hasVul = true
    )
    public Map<java.lang.String, Object> aTaintCase00147(@RequestBody byte[] bytes) {
        Map<java.lang.String, Object> modelMap = new HashMap<>();
        try {
            //  byte[] bytes = cmd.getBytes();
            Runtime.getRuntime().exec(new java.lang.String(bytes, 0, bytes.length, Charset.forName("utf-8")));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
