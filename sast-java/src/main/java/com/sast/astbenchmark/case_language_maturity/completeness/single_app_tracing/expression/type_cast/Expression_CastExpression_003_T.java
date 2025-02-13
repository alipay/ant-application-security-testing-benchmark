package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.type_cast;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->表达式->类型转换->强转表达式-函数调用
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换->强转表达式-函数调用
// bind_url = completeness/single_app_tracing/expression/type_cast/Expression_CastExpression_002_T/{url}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/type_cast")
public class Expression_CastExpression_003_T {
    @GetMapping("Expression_CastExpression_002_T/{url}")
    public Map<String, Object> aTaintCase0156(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseMessage();
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
