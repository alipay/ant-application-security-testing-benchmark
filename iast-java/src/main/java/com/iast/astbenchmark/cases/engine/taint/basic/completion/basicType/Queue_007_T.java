package com.iast.astbenchmark.cases.engine.taint.basic.completion.basicType;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;
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
public class Queue_007_T {

    /**
     * 引用类型queue 作为污点对象
     *
     * @param
     * @return
     */
    @PostMapping("case007")
    @CaseTag(
            caseNo ="aTaintCase007",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->集合(集合对象全为污点)->Queue元素",
            thisMethodTag = "aTaintCase007",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase007(@RequestBody SoureWithQueueBean queueBean) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(queueBean.getQueue().peek());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
