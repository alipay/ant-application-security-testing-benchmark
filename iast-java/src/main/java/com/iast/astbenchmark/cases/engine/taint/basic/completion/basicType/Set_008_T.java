package com.iast.astbenchmark.cases.engine.taint.basic.completion.basicType;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

/**
 * @author CC11001100
 */
@RestController
public class Set_008_T {

    /**
     * 引用类型Set 作为污点对象
     *
     * @param
     * @return
     */
    @PostMapping("case008")
    @CaseTag(
            caseNo ="aTaintCase008",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->集合(集合对象全为污点)->Set元素",
            thisMethodTag = "aTaintCase008",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase008(@RequestBody SoureWithSetBean setBean) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(setBean.getValue().iterator().next());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
