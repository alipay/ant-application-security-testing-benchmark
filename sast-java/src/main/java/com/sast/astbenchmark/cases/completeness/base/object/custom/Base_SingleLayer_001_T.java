package com.sast.astbenchmark.cases.completeness.base.object.custom;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.CmdObject;

/**
 * Introduction 对象中的自定义对象，单层字段
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->自定义对象->单层字段
// compose = Base_SingleLayer_001_T.java
// bind_url = completeness/base/object/custom/Base_SingleLayer_001_T
// assession information end

@RestController()
@RequestMapping("completeness/base/object/custom")
public class Base_SingleLayer_001_T {
    @PostMapping("Base_SingleLayer_001_T")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdObject cmdObject = new CmdObject();
            cmdObject.setCmd(cmd);
            Runtime.getRuntime().exec(cmdObject.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
