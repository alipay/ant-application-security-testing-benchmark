package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.source;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class FromHttpBody_001 {

    /**
     * 污点来自http body  *json
     *
     * @param {"cmd":"ls"}
     * @return //TODO
     */
    @PostMapping("case0033")
    @IastTestCase(
            caseNo ="aTaintCase0033",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点来源识别能力(source)->污点来自http body->json/RequestBody",
            thisMethodTag = "aTaintCase0033",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0033(@RequestBody Map<String, String> json) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(json.get(json.keySet().iterator().next()));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
