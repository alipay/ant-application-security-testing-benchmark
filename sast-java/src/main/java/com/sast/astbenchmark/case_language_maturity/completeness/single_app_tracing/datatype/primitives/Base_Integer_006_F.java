package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->int
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->int
// bind_url = completeness/base/object/javaNative/Base_Integer_006_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Integer_006_F {
    @PostMapping("Base_Integer_006_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable int cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        int i = 0;
        SinkUtil.sink(i);
        modelMap.put("status", "success");
        return modelMap;
    }
}
