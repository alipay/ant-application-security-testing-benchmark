package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive_one_collection.numeric_index_state_no_solver.map;

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
 * Introduction 准确度->对象敏感与域敏感->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)->Queue+Lambda
 * Level 3
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item =  准确度->对象敏感与域敏感->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)->Queue+Lambda
// bind_url = accuracy/object_field_sensitive/field_sensitive_one_collection/numeric_index_state_no_solver/map/QueueWithLambda_001_T
// evaluation information end

@RestController()
@RequestMapping("accuracy/object_field_sensitive/field_sensitive_one_collection/numeric_index_state_no_solver/map")
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
