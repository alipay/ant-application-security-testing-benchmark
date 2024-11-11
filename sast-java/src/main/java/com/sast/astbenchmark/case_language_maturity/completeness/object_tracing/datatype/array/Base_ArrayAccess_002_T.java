package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Array中的对象为污点
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Argument_ArrayAccess-ArrayList+get
// bind_url = completeness/object_tracing/datatype/array/Base_ArrayAccess_002_T
// evaluation information end
@RestController()
@RequestMapping("completeness/object_tracing/datatype/array")
public class Base_ArrayAccess_002_T {
    @PostMapping(value = "Base_ArrayAccess_002_T")
    public Map<String, Object> aTaintCase0111(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Cookie[] cookies = request.getCookies();
            Runtime.getRuntime().exec(cookies[0].getName());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
