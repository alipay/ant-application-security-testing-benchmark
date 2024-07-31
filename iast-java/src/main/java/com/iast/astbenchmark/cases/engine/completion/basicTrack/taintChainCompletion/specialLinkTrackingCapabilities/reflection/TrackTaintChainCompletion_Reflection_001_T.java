package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.specialLinkTrackingCapabilities.reflection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点链路完整度 -> 特殊链路跟踪能力 -> 反射调用方法跟踪
// compose = 
// bind_url = /case0024
// assession information end
public class TrackTaintChainCompletion_Reflection_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 特殊链路跟踪能力->反射调用
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0024")
    @IastTestCase(caseNo = "aTaintCase0024",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->特殊链路跟踪能力->反射调用方法跟踪", thisMethodTag = "aTaintCase0024",
        hasVul = true)
    public Map<String, Object> aTaintCase0024(@RequestParam String cmd)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            Class<StringBuilder> clazz = StringBuilder.class;
            Method method = clazz.getMethod("replace", int.class, int.class, String.class);
            method.setAccessible(true);
            StringBuilder builder = new StringBuilder(cmd);
            Runtime.getRuntime().exec(method.invoke(builder, 0, 1, "l").toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase0024";
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
            "反射调用方法跟踪",
            //
        };
    }

}
