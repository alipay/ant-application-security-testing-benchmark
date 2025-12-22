package com.sast.astbenchmark.case_language_maturity.completeness.dynamic_tracing.dynamic_call;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->动态特性跟踪完整度->反射调用
// scene introduction = 字符串常量
// level = 3
// bind_url = completeness/dynamic_tracing/dynamic_call/Expression_Reflection_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/dynamic_tracing/dynamic_call")
public class Expression_Reflection_002_F {
    @GetMapping("Expression_Reflection_002_F/{cmd}")
    public Map<String, Object> aTaintCase0134(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            String param = "run";
            Class<CmdUtil> clazz = CmdUtil.class;
            Method method = clazz.getMethod("run", String.class);
            method.setAccessible(true);
            //静态方法不需要创建对象实例
            method.invoke(null, param);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
