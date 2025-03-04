package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.asynchronous;

import com.sast.astbenchmark.model.custom.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Introduction 准确度->流敏感分析->异步执行->存储型异步->多线程->Executors
 * Level 4
 * Date 2024-07-31
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->异步执行
// bind_url = accuracy/flow_sensitive/asynchronous/Async_Multithreading_005_T/{cmd}
// evaluation information end

@RestController()
@RequestMapping("accuracy/flow_sensitive/asynchronous")
public class Async_Multithreading_005_T {
    @PostMapping("Async_Multithreading_005_T/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        R r = new R(cmd);
        Executors.newCachedThreadPool().execute(r);
        return r.getMap();
    }
}
