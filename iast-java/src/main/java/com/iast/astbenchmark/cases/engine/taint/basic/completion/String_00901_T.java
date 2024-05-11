package com.iast.astbenchmark.cases.engine.taint.basic.completion;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.CaseTag;

/**
 *
 * Introduction
 * Level X
 * Date 2024-05-11
 * @author CC11001100
 */
// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->字符串对象->String
// compose =
// bind_url = /case00901
// assession information end
@RestController()
public class String_00901_T {

    /**
     * 字符串对象,String
     * 
     * @param cmd
     * @return
     */
    @PostMapping("case00901")
    @CaseTag(caseNo = "aTaintCase00901", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->字符串对象->String",
        thisMethodTag = "aTaintCase00901", thisMethodExpectedResult = true)
    public Map<java.lang.String, Object> aTaintCase00901(@RequestParam java.lang.String cmd) {
        Map<java.lang.String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
