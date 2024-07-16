package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.basicAndUnbox;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 基本数据类型及其封装类型 -> long
// compose = 
// bind_url = /case004
// assession information end
public class TrackTaintObjectCompletion_Long_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider  {

    /**
     * 基础类型long 作为污点对象
     *
     * @param cmd
     * @return
     */
    @GetMapping("case004")
    @IastTestCase(
            caseNo ="aTaintCase004",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->long",
            thisMethodTag = "aTaintCase004",
            hasVul = true
    )
    public Map<String, Object> aTaintCase004(@RequestParam long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase004";
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
                Category.LEVEL5_基本数据类型及其封装类型,
                //
                "long",
                //
        };
    }

    @Override
    public String queryString() {
        return "cmd=10086";
    }

}
