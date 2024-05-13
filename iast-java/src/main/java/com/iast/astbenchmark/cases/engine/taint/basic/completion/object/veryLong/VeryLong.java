package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.veryLong;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.MyCommonTestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class VeryLong {

    /**
     * 特殊链路跟踪能力->超长链路追踪
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0023")
    @IastTestCase(
            caseNo ="aTaintCase0023",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->特殊链路跟踪能力->超长链路追踪->100层",
            thisMethodTag = "aTaintCase0023",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0023(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            /** 递归100次*/
            cmd = MyCommonTestUtil.traceDeepth(cmd, 0, 100);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
