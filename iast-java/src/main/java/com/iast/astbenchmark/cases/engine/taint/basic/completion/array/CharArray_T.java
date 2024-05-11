package com.iast.astbenchmark.cases.engine.taint.basic.completion.array;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

/**
 * @author CC11001100
 */
public class CharArray_T {

    /**
     * 数组 char[] 作为污点对象
     *
     * @param cmd [1,2]
     * @return
     */
    @PostMapping("case0014")
    @CaseTag(
            caseNo ="aTaintCase0014",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->数组(数组对象全为污点)->数组对象char[]",
            thisMethodTag = "aTaintCase0014",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase0014(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        //        if (cmd == null || cmd.length < 1) {
        //            modelMap.put("status", ERROR_STR);
        //            return modelMap;
        //      }
        //char[] data = {(char) cmd[0], (char) cmd[1]};
        char[] data = cmd.toCharArray();
        try {
            //java.io.PrintWriter printWriter = new PrintWriter(System.out);
            //printWriter.print(data);
            Runtime.getRuntime().exec(new String(data));
            modelMap.put("status", SUCCESS_STR);
        } catch  (IOException e)  {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

}
