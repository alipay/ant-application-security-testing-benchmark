package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import com.sast.astbenchmark.common.utils.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Introduction 污点链路样本中的表达式-Lambda表达式
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->Lambda表达式
// compose = Expression_LambdaExpression_001_T.java
// bind_url = completeness/base/chain/astTaint/Expression_LambdaExpression_001_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_LambdaExpression_001_T {
    @GetMapping("Expression_LambdaExpression_001_T")
    public Map<String, Object> aTaintCase017(@RequestBody List<String> urlList) {
        Map<String, Object> modelMap = new HashMap<>();
        {
            urlList.stream().map(url -> {
                try {

                    HttpUtil.doGet(url);
                    return "url can connect: " + url;
                } catch (Exception e) {
                    return "url connect exception: " + url;
                }}).collect(Collectors.toList());
        }
        return modelMap;
    }
}
