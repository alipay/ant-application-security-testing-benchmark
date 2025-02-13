package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.asynchronous;

import com.sast.astbenchmark.model.custom.T;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Introduction 准确度->流敏感->异步->存储型异步->多线程->Thread
 * Level 3
 * Date 2024-07-31
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感->异步->存储型异步->多线程->Thread
// bind_url = accuracy/flow_sensitive/asynchronous/Async_Multithreading_001_T/{cmd}
// evaluation information end

@RestController()
@RequestMapping("accuracy/flow_sensitive/asynchronous")
public class Async_Multithreading_001_T {
    @PostMapping("Async_Multithreading_001_T/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        T t = new T(cmd);
        t.start();
        return t.getMap();
    }
}
