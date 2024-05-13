package com.iast.astbenchmark.cases.engine.taint.basic.completion.stringOperation;

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
public class trim {

    /**
     * aTaintCase0080 传播场景->String操作->trim
     */

    @PostMapping(value = "case0080")
    @IastTestCase(
            caseNo ="aTaintCase0080",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->String操作->trim",
            thisMethodTag = "aTaintCase0080",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0080(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd = cmd.trim();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
