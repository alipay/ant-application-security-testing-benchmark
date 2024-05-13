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
public class StringConstructor_003 {

    @PostMapping(value = "case00144")
    @IastTestCase(
            caseNo ="aTaintCase00144",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->String操作->构造方法->String(char value[], int offset, int count)",
            thisMethodTag = "aTaintCase00144",
            hasVul = true
    )
    public Map<java.lang.String, Object> aTaintCase00144(@RequestParam java.lang.String cmd) {
        Map<java.lang.String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(new java.lang.String(cmd.toCharArray(), 0, 2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
