package com.iast.astbenchmark.cases.engine.taint.basic.completion.hard;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.TaintMethodUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class HardCode {

    /**
     * 污点直接赋值为硬编码值 (硬编码是一种sanitizer方式)
     *
     * @param cmd
     * @return
     */
    @PostMapping(value = "case00141")
    @IastTestCase(
            caseNo ="aTaintCase00141",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点无害化处理能力(sanitizer)->污点直接赋值为硬编码值",
            thisMethodTag = "aTaintCase00141",
            hasVul = false
    )
    public Map<String, Object> aTaintCase00141(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        cmd = "test";
        TaintMethodUtil.sink(cmd);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

}
