package com.sast.astbenchmark.case_language_maturity.accuracy.object_sensitive.interface_class;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.CmdObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-不同对象相同属性
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感->不同对象相同属性
// bind_url = accuracy/object_sensitive/interface_class/ObjectDiffAttribute_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/object_sensitive/interface_class")
public class ObjectDiffAttribute_002_F {
    @PostMapping(value = "ObjectDiffAttribute_002_F")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdObject a = new CmdObject();
            CmdObject b = new CmdObject();
            a.setCmd(cmd);
            b.setCmd("untainted");
            Runtime.getRuntime().exec(b.getCmd());
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
