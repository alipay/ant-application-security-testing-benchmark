package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.multi;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import com.iast.astbenchmark.cases.bean.SourceTestWith100Filedsbject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

/**
 * @author CC11001100
 */
public class MultiLayer_100_001_T {

    @PostMapping("case00922/3")
    @CaseTag(
            caseNo ="aTaintCase00922",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->100",
            thisMethodTag = "aTaintCase00922_3",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00922_3(@RequestBody SourceTestWith100Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd100());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
