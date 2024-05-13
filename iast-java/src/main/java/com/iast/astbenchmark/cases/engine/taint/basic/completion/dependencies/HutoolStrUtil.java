package com.iast.astbenchmark.cases.engine.taint.basic.completion.dependencies;

import cn.hutool.core.util.StrUtil;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class HutoolStrUtil {

    /**  污点链路完整度*/
    /**
     * 特殊链路跟踪能力->三方包方法跟踪
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0022")
    @IastTestCase(
            caseNo ="aTaintCase0022",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->特殊链路跟踪能力->三方包方法跟踪",
            thisMethodTag = "aTaintCase0022",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0022(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(StrUtil.addPrefixIfNot(cmd, "pre"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
