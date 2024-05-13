package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.multi;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean9;
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
public class MultiLayer_10_003_T {

    @PostMapping("case00924")
    @IastTestCase(
            caseNo ="aTaintCase00924",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->多层字段->10层",
            thisMethodTag = "aTaintCase00924",
            hasVul = true
    )
    public Map<String, Object> aTaintCase00924(@RequestBody LayerBaseBean9 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda0());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
