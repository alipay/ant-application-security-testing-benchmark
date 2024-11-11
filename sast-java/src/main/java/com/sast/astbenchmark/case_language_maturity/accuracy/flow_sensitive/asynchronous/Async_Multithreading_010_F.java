package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.asynchronous;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Introduction 异步跟踪能力-多线程 Synchronized
 * Level 3
 * Date 2024-07-31
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感->异步->存储型异步->多线程->Synchronized
// bind_url = accuracy/flow_sensitive/asynchronous/Async_Multithreading_010_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("accuracy/flow_sensitive/asynchronous")
public class Async_Multithreading_010_F {
    private String a;
    @PostMapping("Async_Multithreading_010_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Async_Multithreading_010_F obj = new Async_Multithreading_010_F();
        
        Thread thread1 = new Thread(() -> {
            synchronized (obj) {
                obj.a = "ls";
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (obj) {
                try {
                    Runtime.getRuntime().exec(obj.a);
                    modelMap.put("status", "success");
                } catch (IOException e) {
                    modelMap.put("status", "error");
                }
            }
        });

        thread1.start();
        thread2.start();

        return modelMap;
    }
}
