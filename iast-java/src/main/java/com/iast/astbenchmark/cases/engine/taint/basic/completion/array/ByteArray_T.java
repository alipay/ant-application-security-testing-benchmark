package com.iast.astbenchmark.cases.engine.taint.basic.completion.array;

import com.iast.astbenchmark.analyser.cache.CaseTag;
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
public class ByteArray_T {

    /**
     * 数组 byte[] 作为污点对象
     *  TODO 没有直接传入的sink,先用这个
     * @param cmd
     * @return
     */
    @PostMapping("case0015")
    @CaseTag(
            caseNo ="aTaintCase0015",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->数组(数组对象全为污点)->数组对象byte[]",
            thisMethodTag = "aTaintCase0015",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase0015(@RequestBody byte[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(new String(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
