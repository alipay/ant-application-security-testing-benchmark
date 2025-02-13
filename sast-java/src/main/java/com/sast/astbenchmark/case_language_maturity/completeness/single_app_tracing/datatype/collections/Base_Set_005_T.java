package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.collections;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->集合->Set-remove
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合->Set-remove
// bind_url = completeness/base/object/javaNative/Base_Set_005_T
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Set_005_T {
    @PostMapping("Base_Set_005_T")
    public Map<String, Object> aTaintCase0143(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Set<String> stringSet = new HashSet<>();
        stringSet.add(cmd);
        stringSet.add("_");
        stringSet.remove("_");
        SinkUtil.sink(stringSet);
        modelMap.put("status", "success");
        return modelMap;
    }
}
