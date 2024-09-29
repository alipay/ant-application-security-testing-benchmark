package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.common.utils.SinkUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，List作为污点
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->List
// compose = Base_List_001_T.java || Base_List_002_T.java
// bind_url = completeness/base/object/javaNative/Base_List_002_T
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_List_002_T {
    @PostMapping("Base_List_002_T")
    public Map<String, Object> testcase(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", "error");
            return modelMap;
        }
        SinkUtil.sink(cmd);
        modelMap.put("status", "success");
        return modelMap;
    }
}
