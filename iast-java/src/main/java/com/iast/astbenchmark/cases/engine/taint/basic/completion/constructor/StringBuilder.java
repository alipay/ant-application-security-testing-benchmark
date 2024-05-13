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
public class StringBuilder {

    /**
     * aTaintCase0082 传播场景->StringBuilder操作->构造方法
     */
    @PostMapping(value = "case0082")
    @IastTestCase(
            caseNo ="aTaintCase0082",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->StringBuilder操作->构造方法",
            thisMethodTag = "aTaintCase0082",
            hasVul = true
    )
    public Map<java.lang.String, Object> aTaintCase0082(@RequestParam java.lang.String cmd) {
        Map<java.lang.String, Object> modelMap = new HashMap<>();
        try {
            //SourceTestWithConstract02Bean constract02Bean = new SourceTestWithConstract02Bean();
            Runtime.getRuntime().exec(java.lang.String.valueOf(new java.lang.StringBuilder(cmd)));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }


}
