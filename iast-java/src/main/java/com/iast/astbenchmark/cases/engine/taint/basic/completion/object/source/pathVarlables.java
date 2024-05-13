package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.source;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class pathVarlables {

    /**
     * 污点来自http pathVarlables参数值字母和数字的组合
     *
     * @param cmd a1
     * @return
     */
    @PostMapping(value = "case0044/{cmd}")
    @IastTestCase(
            caseNo ="aTaintCase0044",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点来源识别能力(source)->污点来自http pathVarlables",
            thisMethodTag = "aTaintCase0044",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0044(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
