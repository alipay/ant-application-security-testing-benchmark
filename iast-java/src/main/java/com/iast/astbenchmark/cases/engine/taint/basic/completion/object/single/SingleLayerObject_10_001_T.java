package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.single;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.cases.bean.SourceTestWith10Filedsbject;

/**
 * @author CC11001100
 */
public class SingleLayerObject_10_001_T {

    /**
     * 对象字段->单层字段->10@aTaintCase00921
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00921")
    @IastTestCase(caseNo = "aTaintCase00921",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->10",
        thisMethodTag = "aTaintCase00921", hasVul = true)
    public Map<String, Object> aTaintCase00921(@RequestBody SourceTestWith10Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
