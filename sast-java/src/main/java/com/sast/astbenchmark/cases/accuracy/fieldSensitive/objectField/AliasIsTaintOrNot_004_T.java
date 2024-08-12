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
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染
// compose = !AliasIsTaintOrNot_004_T.java
// bind_url = accuracy/objectSensitive/AliasIsTaintOrNot_004_T
// assession information end

@RestController
@RequestMapping("accuracy/objectSensitive")
public class AliasIsTaintOrNot_004_T {
    @PostMapping(value = "AliasIsTaintOrNot_004_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        CmdObject a = new CmdObject();
        NestedObject b = new NestedObject();
        NestedObject e = b;
        CmdObject c = a;
        CmdObject d = a;
        b.obj = c;
        d.setCmd(cmd);
        CmdObject f = e.obj;
        try {
            Runtime.getRuntime().exec(f.getCmd());
            modelMap.put("status", "success");
        } catch (IOException ex) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    class NestedObject {
        public CmdObject obj = new CmdObject();
    }

}