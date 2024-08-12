package com.sast.astbenchmark.cases.completeness.base.chain.special;

import com.sast.astbenchmark.model.AbsCmdObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-多态 抽象类
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->多态 抽象类
// compose = CallExpression_CustomCode_Class_003_T.java
// bind_url = completeness/base/chain/special/CallExpression_CustomCode_Class_003_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class CallExpression_CustomCode_Class_003_T {
    @GetMapping("CallExpression_CustomCode_Class_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            AbsCmdObject o;
            if(true == false){
                o = new A();
                o.setCmd(cmd);
            } else{
                o = new B();
                o.setCmd(cmd);
            }
            Runtime.getRuntime().exec(o.getCmd());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

class A extends AbsCmdObject{
    @Override
    public String getCmd(){
        return "untained";
    }
}

class B extends AbsCmdObject{
    @Override
    public String getCmd(){
        return cmd;
    }
}
