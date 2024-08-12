package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，int为污点
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->int
// compose = Base_Integer_003_T.java || Base_Integer_004_T.java
// bind_url = completeness/base/object/javaNative/Base_Integer_003_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Integer_003_T {
    @PostMapping("Base_Integer_00e_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable int cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        SinkUtil.sink(cmd);
        modelMap.put("status", "success");
        return modelMap;
    }
}
