package com.iast.astbenchmark.cases.engine.taint.basic.completion.stringOperation.stringBuilder;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class copyOf {

    /**
     * aTaintCase0093 传播场景-char[],byte[]操作->copyOf
     */
    @PostMapping(value = "case0093")
    @IastTestCase(
            caseNo ="aTaintCase0093",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->char[],byte[]操作->copyOf",
            thisMethodTag = "aTaintCase0093",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0093(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            byte[] b2 = Arrays.copyOf(b1, 10);
            Runtime.getRuntime().exec(new String(b2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
