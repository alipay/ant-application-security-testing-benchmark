package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = Argument_ArrayAccess-get+ArrayList
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/Base_ArrayAccess_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/array")
public class Base_ArrayAccess_002_F {
    @PostMapping("Base_ArrayAccess_002_F")
    public Map<String, Object> aTaintCase0110(@RequestParam("cmd") String cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String[]> s = new HashMap<>();
            s.put("cmd", new String[] {"aa", "bb"});
            String cmdStr = s.get("cmd")[0];
            Runtime.getRuntime().exec(cmdStr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
