package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.source;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class FromParameter {

    /**
     * 污点来自http body  form/url-encode getParameter
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0037")
    @IastTestCase(
            caseNo ="aTaintCase0037",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点来源识别能力(source)->污点来自http body->form/url-encode->getParameter",
            thisMethodTag = "aTaintCase0037",
            hasVul = true
    )

    public Map<String, Object> aTaintCase0037(@RequestParam("cmd") String cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmdStr = request.getParameter("cmd");
            Runtime.getRuntime().exec(cmdStr);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }


}
