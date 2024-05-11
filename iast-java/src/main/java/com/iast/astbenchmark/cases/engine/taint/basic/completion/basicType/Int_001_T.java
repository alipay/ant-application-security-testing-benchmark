package com.iast.astbenchmark.cases.engine.taint.basic.completion.basicType;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 基本类型int 作为污点对象
 * Level X
 * Date 2024-05-11
 *
 * @author CC11001100
 */
// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->int
// compose =
// bind_url = /case001
// assession information end
@RestController()
public class Int_001_T {

    /** 污点对象完整度 基础类型 **/
    /**
     * aTaintCase001 基本类型int 作为污点对象
     *
     * @param cmd
     * @return
     */
    @GetMapping("case001")
    @CaseTag(caseNo = "aTaintCase001", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->int",
        thisMethodTag = "aTaintCase001", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase001(@RequestParam int cmd) {
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
