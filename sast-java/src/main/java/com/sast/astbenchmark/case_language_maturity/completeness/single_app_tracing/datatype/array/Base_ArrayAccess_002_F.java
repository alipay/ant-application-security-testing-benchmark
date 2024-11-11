package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Array中的对象为污点
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Argument_ArrayAccess-get+ArrayList
// bind_url = completeness/base/object/javaNative/Base_ArrayAccess_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_ArrayAccess_002_F {
    @PostMapping("Base_ArrayAccess_002_F")
    public Map<String, Object> aTaintCase0110(@RequestParam("cmd") String cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String[]> s = new HashMap<>();
            s.put("cmd", new String[]{"aa", "bb"});
            String cmdStr = s.get("cmd")[0];
            Runtime.getRuntime().exec(cmdStr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
