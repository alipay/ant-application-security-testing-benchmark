package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-反射调用
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->反射调用
// compose = Expression_Reflection_001_T.java
// bind_url = completeness/base/chain/astTaint/Expression_Reflection_001_T/{cmd}/{methodname}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_Reflection_001_T {
    @GetMapping("Expression_Reflection_001_T/{cmd}/{methodname}")
    public Map<String, Object> aTaintCase0134(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Class<CmdUtil> clazz = CmdUtil.class;
            Method method = clazz.getMethod("run", String.class);
            method.setAccessible(true);
            //静态方法不需要创建对象实例
            method.invoke(null, cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
