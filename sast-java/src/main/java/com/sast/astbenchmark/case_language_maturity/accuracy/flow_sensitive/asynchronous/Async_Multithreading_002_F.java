package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.asynchronous;

import com.sast.astbenchmark.model.custom.T;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Introduction 准确度->流敏感->异步执行->存储型异步->多线程->Thread
 * Level 4
 * Date 2024-07-31
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感->异步执行
// bind_url = accuracy/flow_sensitive/asynchronous/Async_Multithreading_002_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("accuracy/flow_sensitive/asynchronous")
public class Async_Multithreading_002_F {
    @PostMapping("Async_Multithreading_002_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        String a = "ls";
        T t = new T(a);
        t.start();
        return t.getMap();
    }
}
