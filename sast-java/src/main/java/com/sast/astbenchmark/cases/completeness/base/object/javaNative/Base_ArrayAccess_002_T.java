package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Array中的对象为污点
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Array
// compose = Base_ArrayAccess_001_T.java || Base_ArrayAccess_002_T.java || Base_ArrayAccess_003_T.java || Base_ArrayAccess_004_T.java
// bind_url = completeness/base/object/javaNative/Base_ArrayAccess_002_T
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
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
