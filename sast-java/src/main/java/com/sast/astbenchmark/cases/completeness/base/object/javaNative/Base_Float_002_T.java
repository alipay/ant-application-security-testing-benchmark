package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Float作为污点
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Float
// compose = Base_Float_002_T.java
// bind_url = completeness/base/object/javaNative/Base_Float_002_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Float_002_T {
    @PostMapping("Base_Float_002_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable Float cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        SinkUtil.sink(cmd);
        modelMap.put("status", "success");
        return modelMap;
    }
}
