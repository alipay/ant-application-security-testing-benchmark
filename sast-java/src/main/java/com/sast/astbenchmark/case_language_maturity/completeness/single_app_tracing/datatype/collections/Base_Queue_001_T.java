package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.collections;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// 注意，case中的 SinkUtil.sink 为自定义Sink，由于评测对基础类型无法直接找到sink点，因此设计出了一个自定义Sink。
// 此评价项（evaluation
// item）的达成为"或"条件（参考compose），一个case是自定义case的形式，另一种case是通过String.valueof函数转化为sink点可以接受的入参类型，两个case达成一个即认为本评价项达成。具体要支持哪个可依据不同引擎的情况而定。
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
// scene introduction = Queue
// level = 2
// bind_url = completeness/single_app_tracing/datatype/collections/Base_Queue_001_T
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/collections")
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
