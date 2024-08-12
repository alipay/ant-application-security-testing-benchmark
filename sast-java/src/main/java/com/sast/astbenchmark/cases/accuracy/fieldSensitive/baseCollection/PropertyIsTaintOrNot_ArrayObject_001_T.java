package com.sast.astbenchmark.cases.accuracy.fieldSensitive;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.CmdObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感->对象部分属性为污点
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象部分属性为污点->List+Array
// compose = !PropertyIsTaintOrNot_ArrayObject_002_F.java && PropertyIsTaintOrNot_ArrayObject_001_T.java
// bind_url = accuracy/fieldSensitive/PropertyIsTaintOrNot_ArrayObject_001_T
// assession information end

@RestController()
@RequestMapping("accuracy/fieldSensitive")
public class PropertyIsTaintOrNot_ArrayObject_001_T {
    @PostMapping(value = "PropertyIsTaintOrNot_ArrayObject_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdObject a = new CmdObject();
            CmdObject b = new CmdObject();
            a.setCmd(cmd);
            a.setCmd2("");
            b.setCmd("cd ~");
            b.setCmd2("cd /");
            CmdObject[] data = new CmdObject[]{a, b};
            Runtime.getRuntime().exec(data[0].getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
