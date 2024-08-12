package com.sast.astbenchmark.cases.accuracy.flowSensitive.flowUnReachable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.CmdObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 数据流不可达->在sink前赋值
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->流敏感->数据流不可达->被sink前赋值
// compose = !AssignOrder_001_F.java && AssignOrder_002_T.java
// bind_url = accuracy/flowSensitive/flowUnReachable/AssignOrder_001_F
// assession information end
@RestController()
@RequestMapping("accuracy/flowSensitive/flowUnReachable")
public class AssignOrder_002_T {
    @PostMapping(value = "AssignOrder_002_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        CmdObject a = new CmdObject();
        CmdObject b = new CmdObject();
        a.setCmd(cmd);
        try {
            b = a;
            Runtime.getRuntime().exec(b.getCmd());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
