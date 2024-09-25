package com.sast.astbenchmark.cases.accuracy.flowSensitive.flowUnReachable;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.CmdObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 数据流不可达-对象属性set赋值为非污点
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->流敏感->数据流不可达->对象属性set赋值为非污点
// compose = !AssignObjectAttribute_001_F.java && AssignObjectAttribute_002_T.java
// bind_url = accuracy/flowSensitive/flowUnReachable/AssignObjectAttribute_001_F
// assession information end
@RestController()
@RequestMapping("accuracy/flowSensitive/flowUnReachable")
public class AssignObjectAttribute_001_F {
    @PostMapping(value = "AssignObjectAttribute_001_F")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a;
            CmdObject b = new CmdObject();
            a = cmd;
            a = "untainted";
            b.setCmd(a);
            Runtime.getRuntime().exec(b.getCmd());
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
