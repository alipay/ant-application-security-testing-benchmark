package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.expression.lambda_expression;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Introduction 污点链路样本中的表达式-Lambda表达式
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->Lambda表达式
// bind_url = completeness/chain_tracing/expression/lambda_expression/Expression_LambdaExpression_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/expression/lambda_expression")
public class Expression_LambdaExpression_002_F {
    @GetMapping("Expression_LambdaExpression_002_F")
    public Map<String, Object> testcase(@RequestBody String url) {
        Map<String, Object> modelMap = new HashMap<>();

        Function<String, String> processUrl = u -> {
            try {
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
