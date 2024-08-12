package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.CmdObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染
// compose = !AliasIsTaintOrNot_003_F.java
// bind_url = accuracy/objectSensitive/AliasIsTaintOrNot_003_F
// assession information end

@RestController
@RequestMapping("accuracy/objectSensitive")
public class AliasIsTaintOrNot_003_F {
    @PostMapping(value = "AliasIsTaintOrNot_003_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        CmdObject b, q, y;
        NestedObject a, p, x;

        a = new NestedObject();
        p = new NestedObject();

        b = new CmdObject();
        q = new CmdObject();

        if (Math.random() < 0.5) {
            x = a;
            y = b;
        } else {
            x = p;
            y = q;
        }
        x.obj = y;
        q.setCmd(cmd);

        try {
            Runtime.getRuntime().exec(a.obj.getCmd());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    class NestedObject {
        public CmdObject obj = new CmdObject();
    }

}