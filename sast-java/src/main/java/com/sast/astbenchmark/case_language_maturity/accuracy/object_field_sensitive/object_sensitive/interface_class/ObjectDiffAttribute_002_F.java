package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.object_sensitive.interface_class;

import com.sast.astbenchmark.model.CmdObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->对象敏感与域敏感->区分不同的类对象、结构体/联合体和字典/列表/数组->对象->不同对象相同属性
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感->区分不同的类对象、结构体/联合体和字典/列表/数组->对象->不同对象相同属性
// bind_url = accuracy/object_field_sensitive/object_sensitive/interface_class/ObjectDiffAttribute_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/object_field_sensitive/object_sensitive/interface_class")
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
