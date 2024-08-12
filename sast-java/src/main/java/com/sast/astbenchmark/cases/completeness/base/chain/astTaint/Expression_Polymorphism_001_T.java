package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-多态 显式构造函数调用
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->多态 显式构造函数调用
// compose = Expression_Polymorphism_001_T.java
// bind_url = completeness/base/chain/astTaint/Expression_Polymorphism_001_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_Polymorphism_001_T {
    @GetMapping("Expression_Polymorphism_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            B b = new B(cmd, "~");
            Runtime.getRuntime().exec(b.getCmds());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

class A {
    private String cmd;

    public A(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return this.cmd;
    }

}

class B extends A {
    private String cmd2;

    public B(String cmd, String cmd2) {
        super(cmd);
        this.cmd2 = cmd2;
    }

    public String getCmds() {
        return super.getCmd() + cmd2;
    }
}
