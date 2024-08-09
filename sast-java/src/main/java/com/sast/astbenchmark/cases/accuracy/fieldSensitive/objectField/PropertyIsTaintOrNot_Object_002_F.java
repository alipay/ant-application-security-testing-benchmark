package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.CmdObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-PropertyTaint
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->PropertyTaint
// compose = !PropertyIsTaintOrNot_Object_002_F.java && PropertyIsTaintOrNot_Object_001_T.java
// bind_url = accuracy/fieldSensitive/PropertyIsTaintOrNot_Object_002_F
// assession information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/objectField")
public class PropertyIsTaintOrNot_Object_002_F {
    @PostMapping(value = "PropertyIsTaintOrNot_Object_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdObject cmdObject = new CmdObject();
            cmdObject.setCmd(cmd);
            cmdObject.setCmd2("cd /");
            Runtime.getRuntime().exec(cmdObject.getCmd2());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
