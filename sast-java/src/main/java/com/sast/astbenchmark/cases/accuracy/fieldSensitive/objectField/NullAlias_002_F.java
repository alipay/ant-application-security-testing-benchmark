package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.DataClass;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-NullAlias
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->NullAlias
// compose = NullAlias_001_T.java && !NullAlias_002_F.java
// bind_url = accuracy/fieldSensitive/objectField/NullAlias_002_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class NullAlias_002_F {
    @PostMapping(value = "NullAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            DataClass dc = new DataClass();
            DataClass dc2 = null;
            DataClass dc3 = new DataClass();

            dc2 = dc3;

            dc2.next = dc;
            dc2 = null;
            dc3.next = null;

            String a = cmd;
            dc.data = a;
            dc = null;

            Runtime.getRuntime().exec(dc3.next.data);  // NPE
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
