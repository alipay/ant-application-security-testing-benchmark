package com.sast.astbenchmark.cases.completeness.base.chain.special;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-非常规类函数-嵌套成员类
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->非常规类函数->嵌套成员类
// compose = CallExpression_CustomCode_Class_001_T.java
// bind_url = completeness/base/chain/special/CallExpression_CustomCode_Class_001_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special")
public class CallExpression_CustomCode_Class_001_T {
    @PostMapping(value = "CallExpression_CustomCode_Class_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Outer a = new Outer(cmd);
        try {
            Runtime.getRuntime().exec(a.testMember());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

class Outer {
    private String cmd;

    Outer(String cmd){
        this.cmd = cmd;
    }

    class MemberClass {
        String getCmd(){
            return Outer.this.cmd;
        }
    }

    public String testMember(){
        Outer.MemberClass member = new MemberClass();
        return member.getCmd();
    }
}