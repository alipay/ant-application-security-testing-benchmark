package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.lambda_expression;

import com.sast.astbenchmark.common.utils.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Introduction 完整度->单应用跟踪完整度->表达式->Lambda表达式
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式
// bind_url = completeness/single_app_tracing/expression/lambda_expression/Expression_LambdaExpression_001_T
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/lambda_expression")
public class Expression_LambdaExpression_001_T {
    @GetMapping("Expression_LambdaExpression_001_T")
    public Map<String, Object> aTaintCase017(@RequestBody String url) {
        Map<String, Object> modelMap = new HashMap<>();

        Function<String, String> processUrl = u -> {
            try {
                HttpUtil.doGet(u);
                return "url can connect: " + u;
            } catch (Exception e) {
                return "url connect exception: " + u;
            }
        };
        String result = processUrl.apply(url);

        modelMap.put("status", result);
        return modelMap;
    }
}
