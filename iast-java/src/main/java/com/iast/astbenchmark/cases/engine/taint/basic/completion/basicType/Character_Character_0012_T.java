package com.iast.astbenchmark.cases.engine.taint.basic.completion.basicType;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

/**
 * @author CC11001100
 */
public class Character_Character_0012_T {

    /**
     * 基本数据类型的封装类型 Character 作为污点对象
     *
     * @param cmd 测试数据使用（0~9）
     * @return
     */
    @PostMapping("case0012")
    @CaseTag(
            caseNo ="aTaintCase0012",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->Character",
            thisMethodTag = "aTaintCase0012",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase0012(@RequestParam Character cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
