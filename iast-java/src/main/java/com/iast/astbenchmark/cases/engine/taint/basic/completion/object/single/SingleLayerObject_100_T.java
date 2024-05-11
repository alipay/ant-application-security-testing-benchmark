package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.single;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import com.iast.astbenchmark.cases.bean.SourceTestWith10Filedsbject;
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
public class SingleLayerObject_100_T {

    @PostMapping("case00921/3")
    @CaseTag(
            caseNo ="aTaintCase00921",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->10",
            thisMethodTag = "aTaintCase00921_3",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00921_3(@RequestBody SourceTestWith10Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd10());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
