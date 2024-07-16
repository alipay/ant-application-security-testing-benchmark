package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.array;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_CharArray_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 数组 char[] 作为污点对象
     *
     * @param cmd [1,2]
     * @return
     */
    @PostMapping("case0014")
    public Map<String, Object> aTaintCase0014(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        // if (cmd == null || cmd.length < 1) {
        // modelMap.put("status", ERROR_STR);
        // return modelMap;
        // }
        // char[] data = {(char) cmd[0], (char) cmd[1]};
        char[] data = cmd.toCharArray();
        try {
            // java.io.PrintWriter printWriter = new PrintWriter(System.out);
            // printWriter.print(data);
            Runtime.getRuntime().exec(new String(data));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase0014";
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点对象完整度,
            //
            Category.LEVEL5_数组_数组对象全为污点,
            //
            "数组对象char[]",
            //
        };
    }

    @Override
    public String requestBodyString() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_STRING_BODY;
    }

}
