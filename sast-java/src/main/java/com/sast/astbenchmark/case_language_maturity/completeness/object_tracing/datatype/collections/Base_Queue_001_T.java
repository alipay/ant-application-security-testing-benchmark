package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.collections;

import com.sast.astbenchmark.common.utils.SinkUtil;
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
 * Introduction 对象中的简单类型对象，Queue对象为污点
 * Level 2
 * Date 2024-05-09
 * 注意，case中的 SinkUtil.sink 为自定义Sink，由于评测对基础类型无法直接找到sink点，因此设计出了一个自定义Sink。
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Queue
// bind_url = completeness/object_tracing/datatype/collections/Base_Queue_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/object_tracing/datatype/collections")
public class Base_Queue_001_T {
    @PostMapping("Base_Queue_001_T")
    public Map<String, Object> aTaintCase0142(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", "error");
            return modelMap;
        }
        Queue<String> queue = new LinkedBlockingQueue();
        queue.add(cmd.get(0));
        SinkUtil.sink(queue);
        modelMap.put("status", "success");
        return modelMap;
    }
}
