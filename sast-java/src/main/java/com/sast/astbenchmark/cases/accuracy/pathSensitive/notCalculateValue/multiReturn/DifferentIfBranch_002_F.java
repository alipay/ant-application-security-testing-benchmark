package com.sast.astbenchmark.cases.accuracy.pathSensitive.notCalculateValue.multiReturn;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 路径敏感-不涉及求解问题-多个return语句-不同if分支
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->路径敏感->不涉及求解问题->多个return语句->不同if分支
// compose = !DifferentIfBranch_002_F.java && DifferentIfBranch_001_T.java
// bind_url = accuracy/pathSensitive/notCalculateValue/multiReturn/DifferentIfBranch_002_F
// assession information end
@RestController
@RequestMapping("accuracy/pathSensitive/notCalculateValue/multiReturn")
public class DifferentIfBranch_002_F {
    @PostMapping(value = "DifferentIfBranch_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String exec = getCmdWithPathSensitive(cmd);
        try {
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    private String getCmdWithPathSensitive(String cmd) {
        int x = -1;
        if (x > 0) {
            return cmd;
        }
        else {
            return "ls";
        }
    }
}
