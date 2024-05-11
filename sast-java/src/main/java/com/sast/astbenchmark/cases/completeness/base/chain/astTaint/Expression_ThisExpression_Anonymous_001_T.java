package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import com.sast.astbenchmark.service.SSRFShowManageImpl;
import com.sast.astbenchmark.service.SSRFShowManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-this表达式+匿名函数
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->this表达式+匿名函数
// compose = Expression_ThisExpression_001_T.java
// bind_url = completeness/base/chain/astTaint/Expression_ThisExpression_001_T/{url}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_ThisExpression_Anonymous_001_T {
    private SSRFShowManager ssrfShowManager = new SSRFShowManageImpl();
    @GetMapping("Expression_ThisExpression_Anonymous_001_T/{url}")
    public Map<String, Object> aTaintCase0131(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

            this.ssrfShowManager.anonymousFacade(url);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
