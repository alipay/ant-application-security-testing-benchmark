package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Introduction 污点链路样本中的语句-if语句
 * Level X
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->if语句
// compose = Statement_IfStatement_003_T.java
// bind_url = completeness/base/chain/astTaint/Statement_IfStatement_003_T
// assession information end
@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_IfStatement_003_T {
    @PostMapping("Statement_IfStatement_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a1 = new A();
            A a2 = new A();
            a2.b = cmd;
            B b = new B();
            if (new Random().nextBoolean())
                b.attr = a1;
            else
                b.attr = a2;
            Runtime.getRuntime().exec(b.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
