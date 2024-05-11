package com.sast.astbenchmark.cases.accuracy.fieldSensitive;

import com.google.common.collect.Queues;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Introduction 域敏感->对象部分属性为污点
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象部分属性为污点->Queue+Lambda
// compose = !PropertyIsTaintOrNot_Queue_Lambda_002_F.java && PropertyIsTaintOrNot_Queue_Lambda_001_T.java
// bind_url = completeness/base/chain/astTaint/PropertyIsTaintOrNot_Queue_Lambda_001_T
// assession information end

@RestController()
@RequestMapping("accuracy/fieldSensitive/propertyIsTaintOrNot")
public class PropertyIsTaintOrNot_Queue_Lambda_001_T {
    @PostMapping(value = "PropertyIsTaintOrNot_Queue_Lambda_001_T")
    public Map<String, Object> aTaintCase025(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Queue<String> queue = Queues.newArrayDeque();
            queue.add(cmd);
            queue.add("cd /");
            queue.add("cd ~");
            queue.stream().forEach(e->{
                if(cmd.equals(e)){
                    try {
                        Runtime.getRuntime().exec(e);
                    } catch (IOException ex) {
                    }
                }
            });

            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
