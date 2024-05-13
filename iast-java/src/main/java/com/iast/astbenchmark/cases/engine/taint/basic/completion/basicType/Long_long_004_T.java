package com.iast.astbenchmark.cases.engine.taint.basic.completion.basicType;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

/**
 * Introduction
 * Level X
 * Date 2024-05-11
 *
 * @author CC11001100
 */
// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->long
// compose =
// bind_url = /case004
// assession information end
public class Long_long_004_T {

    /**
     * 基础类型long 作为污点对象
     *
     * @param cmd
     * @return
     */
    @GetMapping("case004")
    @IastTestCase(
            caseNo ="aTaintCase004",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->long",
            thisMethodTag = "aTaintCase004",
            hasVul = true
    )
    public Map<String, Object> aTaintCase004(@RequestParam long cmd) {
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
