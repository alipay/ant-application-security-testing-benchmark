package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_value_passing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-变量赋值表达式直接引入而不是import
 * Level X
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->相同函数调用不同参数->变量赋值表达式直接引入而不是import
// bind_url = accuracy/context_sensitive/argument_value_passing/Expression_CallExpression_Package_001_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_value_passing")
public class Expression_CallExpression_Package_001_T {
    @GetMapping("Expression_NewExpression_Package_001_T/{url}")
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
