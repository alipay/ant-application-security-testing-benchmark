package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.collections;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->集合->Queue
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合->Queue
// bind_url = completeness/base/object/javaNative/Base_Queue_003_T
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Queue_003_T {
    @PostMapping("Base_Queue_003_T")
    public Map<String, Object> aTaintCase0142(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", "error");
            return modelMap;
        }
        Queue<String> queue = new LinkedBlockingQueue();
        try {
            queue.add(cmd.get(0));
            Runtime.getRuntime().exec(queue.peek());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
