package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-多态
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->多态
// compose = Expression_Polymorphism_001_T.java && !Expression_Polymorphism_002_F.java
// bind_url = completeness/base/chain/astTaint/Expression_Polymorphism_001_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_Polymorphism_002_F {
    @GetMapping("Expression_Polymorphism_002_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            PS ps = new PS(cmd, "~");
            Runtime.getRuntime().exec(ps.getCmd("ls"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

class P {
    private String cmd;

    public P(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return this.cmd;
    }

}

class PS extends P {
    private String cmd2;

    public PS(String cmd, String cmd2) {
        super(cmd);
        this.cmd2 = cmd2;
    }

    public String getCmd() {
        return super.getCmd() + this.cmd2;
    }

    public String getCmd(String cmd) {
        return cmd + this.cmd2;
    }

}
