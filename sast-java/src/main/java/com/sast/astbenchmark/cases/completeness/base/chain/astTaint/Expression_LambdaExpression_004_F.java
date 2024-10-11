package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Introduction 污点链路样本中的表达式-Lambda表达式-方法引用
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->Lambda表达式-方法引用
// compose = Expression_LambdaExpression_003_T.java && !Expression_LambdaExpression_004_F.java
// bind_url = completeness/base/chain/astTaint/Expression_LambdaExpression_004_F
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_LambdaExpression_004_F {
    @GetMapping("Expression_LambdaExpression_004_F")
    public Map<String, Object> testcase(@RequestBody String url) {
        Map<String, Object> modelMap = new HashMap<>();

        List<String> urlList = Collections.singletonList(url);

        urlList.stream()
            .map(Expression_LambdaExpression_003_T::processUrl)
            .collect(Collectors.toList());
            
        return modelMap;
    }

    static String processUrl(String url) {
        try {
            return "url can connect: " + url;
        } catch (Exception e) {
            return "url connect exception: " + url;
        }
    }
}
