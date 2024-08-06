package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.D;
import com.sast.astbenchmark.model.alias.E;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-HeapOverwriteAlias-scene5
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象属性->对象指针->HeapOverwriteAlias-scene5
// compose = HeapOverwriteAlias_010_T.java && HeapOverwriteAlias_011_T.java && !HeapOverwriteAlias_012_F.java
// bind_url = accuracy/fieldSensitive/objectField/HeapOverwriteAlias_010_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class HeapOverwriteAlias_010_T {
    @PostMapping(value = "HeapOverwriteAlias_010_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            D d = new D();
            E e = new E();
            d.setF(e);
            Runtime.getRuntime().exec(d.e.str);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
