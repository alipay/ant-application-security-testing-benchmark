package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.reflection;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class Reflection_001 {

    /**
     * 特殊链路跟踪能力->反射调用
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0024")
    @IastTestCase(
            caseNo ="aTaintCase0024",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->特殊链路跟踪能力->反射调用方法跟踪",
            thisMethodTag = "aTaintCase0024",
            hasVul = true
    )
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


}
