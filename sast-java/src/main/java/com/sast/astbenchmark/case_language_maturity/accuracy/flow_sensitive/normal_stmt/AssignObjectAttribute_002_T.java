package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.normal_stmt;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.CmdObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->流敏感分析->常规顺序执行语句->数据流不可达->对象属性set赋值为非污点
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->常规顺序执行语句
// bind_url = accuracy/flow_sensitive/normal_stmt/AssignObjectAttribute_002_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/flow_sensitive/normal_stmt")
public class AssignObjectAttribute_002_T {
    @PostMapping(value = "AssignObjectAttribute_002_T")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a;
            CmdObject b = new CmdObject();
            a = cmd;
            b.setCmd(a);
            a = "untainted";
            Runtime.getRuntime().exec(b.getCmd());
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
