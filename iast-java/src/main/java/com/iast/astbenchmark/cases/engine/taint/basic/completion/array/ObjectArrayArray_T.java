package com.iast.astbenchmark.cases.engine.taint.basic.completion.array;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.cases.bean.SourceTestObject;
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
public class ObjectArrayArray_T {

    @PostMapping("case00926")
    @IastTestCase(
            caseNo ="aTaintCase00926",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->数组(数组对象全为污点)->多维数组对象的元素",
            thisMethodTag = "aTaintCase00926",
            hasVul = true
    )
    public Map<String, Object> aTaintCase00926(@RequestBody SourceTestObject[][] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd[1][1].getCmd());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
