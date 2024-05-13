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
public class copyOfRange {

    /**
     * aTaintCase0094 传播场景-char[],byte[]操作-->copyOfRange
     */
    @PostMapping(value = "case0094")
    @IastTestCase(
            caseNo ="aTaintCase0094",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->char[],byte[]操作->copyOfRange",
            thisMethodTag = "aTaintCase0094",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0094(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            byte[] b2 = Arrays.copyOfRange(b1, 0, 2);
            Runtime.getRuntime().exec(new String(b2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
