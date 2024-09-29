package com.sast.astbenchmark.cases.completeness.base.chain.special.custom;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数-Serializable接口
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->Serializable接口
// compose = CallExpression_CustomCode_Interface_001_T.java && !CallExpression_CustomCode_Interface_002_F.java
// bind_url = completeness/base/chain/special/custom/CallExpression_CustomCode_Interface_002_F
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special/custom")
public class CallExpression_CustomCode_Interface_002_F {
    @PostMapping(value = "CallExpression_CustomCode_Interface_002_F")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        S s1 = new S(cmd);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(new S("ls"));
            byte[] bytes = out.toByteArray(); 
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream iis = new ObjectInputStream(in);
            S s2 = (S) iis.readObject();
            oos.close();
            iis.close();
            Runtime.getRuntime().exec(s2.s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    private static class S implements Serializable {
            
        public String s;
    
        public S(String s) {
            this.s = s;
        }  
    }
}
