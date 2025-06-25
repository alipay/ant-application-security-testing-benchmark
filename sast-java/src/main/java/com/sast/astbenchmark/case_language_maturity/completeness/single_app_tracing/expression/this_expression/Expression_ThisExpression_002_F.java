package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.this_expression;

import com.sast.astbenchmark.service.SSRFShowManageImpl;
import com.sast.astbenchmark.service.SSRFShowManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->this表达式
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/expression/this_expression/Expression_ThisExpression_002_F/{url}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/this_expression")
public class Expression_ThisExpression_002_F {
    private SSRFShowManager ssrfShowManager = new SSRFShowManageImpl();

    @GetMapping("Expression_ThisExpression_002_F/{url}")
    public Map<String, Object> aTaintCase0130(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            url = new String("clean");
            this.ssrfShowManager.connectFacade(url);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
