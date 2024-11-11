package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.map;

import com.google.common.collect.Queues;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Introduction 域敏感-Queue+Lambda
 * Level 4
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->域敏感->容器->Queue+Lambda
// bind_url = accuracy/field_sensitive/map/QueueWithLambda_001_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/field_sensitive/map")
public class QueueWithLambda_001_T {
    @PostMapping(value = "QueueWithLambda_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Queue<String> queue = Queues.newArrayDeque();
            queue.add(cmd);
            queue.add("cd /");
            queue.add("cd ~");
            queue.stream().forEach(e -> {
                if (cmd.equals(e)) {
                    try {
                        Runtime.getRuntime().exec(e);
                    } catch (IOException ex) {
                    }
                }
            });

            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
