package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.asynchronous;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->流敏感->异步执行->存储型异步->多线程->Runnable
 * Level 4
 * Date 2024-07-31
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感->异步执行->存储型异步->多线程->Runnable
// bind_url = accuracy/flow_sensitive/asynchronous/Async_Multithreading_003_T/{cmd}
// evaluation information end

@RestController()
@RequestMapping("accuracy/flow_sensitive/asynchronous")
public class Async_Multithreading_003_T {
    @PostMapping("Async_Multithreading_003_T/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Runtime.getRuntime().exec(cmd);
                    modelMap.put("status", "success");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    modelMap.put("status", "success");
                }
            }
        }).start();
        return modelMap;
    }
}
