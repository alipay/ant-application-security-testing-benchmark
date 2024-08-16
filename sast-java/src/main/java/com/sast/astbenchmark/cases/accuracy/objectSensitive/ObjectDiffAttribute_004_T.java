package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.CmdObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-不同对象相同属性-scene2
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->不同对象相同属性-scene2
// compose = !ObjectDiffAttribute_005_F.java && ObjectDiffAttribute_004_T.java && ObjectDiffAttribute_003_T.java
// bind_url = accuracy/objectSensitive/ObjectDiffAttribute_003_T
// assession information end
@RestController()
@RequestMapping("accuracy/objectSensitive")
public class ObjectDiffAttribute_004_T {
    @PostMapping(value = "ObjectDiffAttribute_004_T")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a;
            CmdObject b = new CmdObject();
            a = cmd;
            b.setCmd(cmd);
            b.setCmd("untainted");
            Runtime.getRuntime().exec(a);
            Runtime.getRuntime().exec(b.getCmd());
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
