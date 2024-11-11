package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.expression.this_expression;

import com.sast.astbenchmark.service.SSRFShowManageImpl;
import com.sast.astbenchmark.service.SSRFShowManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-this表达式+lambda表达式
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->表达式->this表达式+lambda表达式
// bind_url = completeness/chain_tracing/expression/this_expression/Expression_ThisExpression_Lambda_001_T/{url}
// evaluation information end

@RestController()
@RequestMapping("completeness/chain_tracing/expression/this_expression")
public class Expression_ThisExpression_Lambda_001_T {
    private SSRFShowManager ssrfShowManager = new SSRFShowManageImpl();

    @GetMapping("Expression_ThisExpression_Lambda_001_T/{url}")
    public Map<String, Object> aTaintCase0132(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

            this.ssrfShowManager.selfAnonymousFacade(url);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
