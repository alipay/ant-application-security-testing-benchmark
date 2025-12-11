package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.asynchronous;

import com.sast.astbenchmark.model.custom.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Executors;

// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->异步执行
// scene introduction = 存储型异步->多线程->Executors
// level = 4
// bind_url = accuracy/flow_sensitive/asynchronous/Async_Multithread_006_F
// evaluation information end

@RestController()
@RequestMapping("accuracy/flow_sensitive/asynchronous")
public class Async_Multithread_006_F {
    @PostMapping("Async_Multithread_006_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        String a = "ls";
        R r = new R(a);
        Executors.newCachedThreadPool().execute(r);
        return r.getMap();
    }
}
