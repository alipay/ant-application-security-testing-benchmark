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
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染
// compose = !AliasIsTaintOrNot_006_F.java && AliasIsTaintOrNot_005_T.java
// bind_url = accuracy/objectSensitive/AliasIsTaintOrNot_006_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class AliasIsTaintOrNot_006_F {
    @PostMapping(value = "AliasIsTaintOrNot_006_F")
    public Map<String, Object> aTaintCase003_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        B b1 = new B();
        B b2 = new B();
        b2.attr = b1.attr;
        doUnalias(b2);
        b1.attr.b = cmd;
        try {
            Runtime.getRuntime().exec(b2.attr.b);
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

    private void doUnalias(B b2) {
        b2.attr = new A();
    }
}