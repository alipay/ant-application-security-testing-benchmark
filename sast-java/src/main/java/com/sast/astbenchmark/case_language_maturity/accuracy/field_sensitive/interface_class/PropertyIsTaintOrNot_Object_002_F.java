package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.interface_class;

import com.sast.astbenchmark.model.CmdObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-PropertyTaint
 * Level 3
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->域敏感->对象属性->对象指针->PropertyTaint
// bind_url = accuracy/field_sensitive/interface_class/PropertyIsTaintOrNot_Object_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/field_sensitive/interface_class")
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
