package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_return_value_passing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item =  准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->变量赋值表达式直接引入而不是import
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/CallExpression_Package_001_T/{url}
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing")
public class CallExpression_Package_001_T {
    @GetMapping("CallExpression_Package_001_T/{url}")
    public Map<String, Object> aTaintCase0157(@PathVariable String url) {

        Map<String, Object> modelMap = new HashMap<>();
        try {
            String result = com.sast.astbenchmark.common.utils.HttpUtil.doGet(url);
            modelMap.put("status", "success");
            modelMap.put("result", result);
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
