package com.sast.astbenchmark.cases.accuracy.fieldSensitive;

import com.sast.astbenchmark.model.CmdObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感->对象部分属性为污点
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象部分属性为污点->自定义对象
// compose = !PropertyIsTaintOrNot_Object_002_F.java && PropertyIsTaintOrNot_Object_001_T.java
// bind_url = completeness/base/chain/astTaint/PropertyIsTaintOrNot_Object_001_T
// assession information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/propertyIsTaintOrNot")
public class PropertyIsTaintOrNot_Object_001_T {
    @PostMapping(value = "PropertyIsTaintOrNot_Object_001_T")
    public Map<String, Object> aTaintCase022(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdObject simpleBean = new CmdObject();
            simpleBean.setCmd(cmd);
            simpleBean.setCmd2("cd /");
            Runtime.getRuntime().exec(simpleBean.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
