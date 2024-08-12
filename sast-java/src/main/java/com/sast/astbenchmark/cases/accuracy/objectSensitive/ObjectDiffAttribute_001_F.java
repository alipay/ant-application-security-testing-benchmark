package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.CmdObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-不同对象相同属性
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->不同对象相同属性
// compose = !ObjectDiffAttribute_001_F.java
// bind_url = accuracy/objectSensitive/ObjectDiffAttribute_001_F
// assession information end
@RestController()
@RequestMapping("accuracy/objectSensitive")
public class ObjectDiffAttribute_001_F {
    @PostMapping(value = "ObjectDiffAttribute_001_F")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdObject a = new CmdObject();
            CmdObject b = new CmdObject();
            a.setCmd(cmd);
            b.setCmd("hello world");
            Runtime.getRuntime().exec(b.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
