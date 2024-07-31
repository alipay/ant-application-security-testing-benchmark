package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.specialLinkTrackingCapabilities.reflection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点链路完整度 -> 特殊链路跟踪能力 -> 调用native方法
// compose = 
// bind_url = /case0025
// assession information end
public class TrackTaintChainCompletion_Native_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 特殊链路跟踪能力->调用native 方法
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0025")
    @IastTestCase(caseNo = "aTaintCase0025",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->特殊链路跟踪能力->调用native方法",
        thisMethodTag = "aTaintCase0025", hasVul = true)
    public Map<String, Object> aTaintCase0025(@RequestBody String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            // String[] b = {"ls","b"};
            String[] b = {"oo"};
            // 调用了这个native方法，看看是不是能追踪得到这个传播过程
            System.arraycopy(cmd, 0, b, 0, 1);
            Runtime.getRuntime().exec(b);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase0025";
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
            Category.LEVEL4_污点链路完整度,
            //
            Category.LEVEL5_特殊链路跟踪能力,
            //
            "调用native方法",
            //
        };
    }

}
