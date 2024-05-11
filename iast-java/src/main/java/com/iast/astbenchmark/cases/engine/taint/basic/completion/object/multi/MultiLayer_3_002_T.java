package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.multi;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean2;

/**
 * @author CC11001100
 */
public class MultiLayer_3_002_T {

    @PostMapping("case00923/2")
    @CaseTag(caseNo = "aTaintCase00923",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->多层字段->3层",
        thisMethodTag = "aTaintCase00923_2", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00923_2(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
