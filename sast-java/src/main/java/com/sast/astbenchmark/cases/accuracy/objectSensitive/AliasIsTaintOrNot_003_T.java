package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染
// compose = !AliasIsTaintOrNot_004_F.java && AliasIsTaintOrNot_003_T.java
// bind_url = accuracy/objectSensitive/AliasIsTaintOrNot_003_T
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class AliasIsTaintOrNot_003_T {
    @PostMapping(value = "AliasIsTaintOrNot_003_T")
    public Map<String, Object> aliasIsTaintOrNot_003_T(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        B b1 = new B();
        b1.attr = new A();
        B b2 = new B();
        alias(b1, b2);
        b2.attr.b = cmd;
        try {
            Runtime.getRuntime().exec(b1.attr.b);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    class B {
        public A attr;

        public B() {
            attr = new A();
        }

        public void setAttr(A attr) {
            this.attr = attr;
        }
    }
    class A {
        public String b = "Y";
        public String c = "X";
        public int i = 0;
    }

    private void alias(B b1, B b2) {
        b2.attr = b1.attr;
    }
}