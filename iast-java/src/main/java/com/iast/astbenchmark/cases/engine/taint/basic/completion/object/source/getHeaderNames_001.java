package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.source;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class getHeaderNames_001 {

    /**
     * 污点来自http headers   getHeaderNames
     *
     * @param request
     * @return
     */
    @PostMapping(value = "case00139")
    @IastTestCase(
            caseNo ="aTaintCase00139",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点来源识别能力(source)->污点来自http header->getHeaderNames",
            thisMethodTag = "aTaintCase00139",
            hasVul = true
    )
    public Map<String, Object> aTaintCase00139(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmd = request.getHeaderNames().nextElement();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
