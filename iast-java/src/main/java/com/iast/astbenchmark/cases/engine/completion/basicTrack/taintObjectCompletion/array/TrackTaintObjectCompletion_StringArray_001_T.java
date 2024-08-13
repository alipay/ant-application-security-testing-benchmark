package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.array;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 数组(数组对象全为污点) -> 数组对象String[]
// compose = TrackTaintObjectCompletion_StringArray_001_T.java
// bind_url = /case0013
// assession information end
public class TrackTaintObjectCompletion_StringArray_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 数组 String[] 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0013")
    public Map<String, Object> aTaintCase0013(@RequestBody String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase0013";
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
            "数组对象String[]",
            //
        };
    }

    @Override
    public Object requestBodyBean() {
        return new String[] {"cd /", "ls"};
    }

}
