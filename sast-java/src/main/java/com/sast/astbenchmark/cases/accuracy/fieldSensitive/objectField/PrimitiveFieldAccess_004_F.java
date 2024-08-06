package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.PrimitiveData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-PrimitiveFieldAccess-scene2
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->PrimitiveFieldAccess-scene2
// compose = PrimitiveFieldAccess_003_T.java && !PrimitiveFieldAccess_004_F.java
// bind_url = accuracy/fieldSensitive/objectField/PrimitiveFieldAccess_004_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class PrimitiveFieldAccess_004_F {
    @PostMapping(value = "PrimitiveFieldAccess_004_F")
    public Map<String, Object> testcase(@RequestParam int id) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            PrimitiveData i = new PrimitiveData();
            PrimitiveData b = i;
            b.setIntData(100);
            Runtime.getRuntime().exec("cat /some/path/" + i.intData + ".png");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}