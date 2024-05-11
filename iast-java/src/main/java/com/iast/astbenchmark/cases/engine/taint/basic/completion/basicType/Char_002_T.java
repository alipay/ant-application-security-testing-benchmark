package com.iast.astbenchmark.cases.engine.taint.basic.completion.basicType;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

/**
 * Introduction aTaintCase002 基本类型char 作为污点对象
 * Level X
 * Date 2024-05-11
 *
 * @author CC11001100
 */
// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->char
// compose =
// bind_url = /case002
// assession information end
@RestController()
public class Char_002_T {

    /**
     * aTaintCase002 基本类型char 作为污点对象
     * 测试数据传（0～9）
     *
     * @return
     */
    @GetMapping("case002")
    @CaseTag(
            caseNo ="aTaintCase002",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->char",
            thisMethodTag = "aTaintCase002",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase002(@RequestParam char cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
