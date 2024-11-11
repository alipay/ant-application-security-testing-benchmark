package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.expression.conditional_expression;

import org.apache.commons.lang.StringUtils;
import com.sast.astbenchmark.service.SSRFShowManageImpl;
import com.sast.astbenchmark.service.SSRFShowManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-三目运算符
 * Level 3
 * Date 2024-05-09
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->三目运算符
// bind_url = completeness/chain_tracing/expression/conditional_expression/Expression_TernaryOperator_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/expression/conditional_expression")
public class Expression_TernaryOperator_001_T {
    private SSRFShowManager ssrfShowManager = new SSRFShowManageImpl();

    @GetMapping("Expression_TernaryOperator_001_T/{url}")
    public Map<String, Object> aTaintCase0133(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String realUrl = StringUtils.isBlank(url) ? url : "https://www.alipay.com";
            this.ssrfShowManager.selfAnonymousFacade(realUrl);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
