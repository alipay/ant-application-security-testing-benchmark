package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.collections;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Introduction 对象中的简单类型对象，Queue对象为污点
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Queue-remove
// bind_url = completeness/base/object/javaNative/Base_Queue_005_T
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Queue_005_T {
    @PostMapping("Base_Queue_005_T")
    public Map<String, Object> aTaintCase0142(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Queue<String> queue = new LinkedBlockingQueue();
        queue.add("_");
        queue.add(cmd);
        queue.remove();
        SinkUtil.sink(queue);
        modelMap.put("status", "success");
        return modelMap;
    }
}
