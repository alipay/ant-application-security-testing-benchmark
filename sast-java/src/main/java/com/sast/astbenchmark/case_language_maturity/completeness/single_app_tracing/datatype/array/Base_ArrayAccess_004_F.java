package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = Argument_ArrayAccess-ArrayList+get
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/Base_ArrayAccess_004_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/array")
public class Base_ArrayAccess_004_F {
    @PostMapping(value = "Base_ArrayAccess_004_F")
    public Map<String, Object> aTaintCase0111(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Cookie[] cookies = new Cookie[] {new Cookie("username", "_")};
            Runtime.getRuntime().exec(cookies[0].getName());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
