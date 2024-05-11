package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Array中的对象为污点
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Argument ArrayAccess
// compose = Base_ArrayAccess_003_T.java
// bind_url = completeness/base/object/javaNative/Base_ArrayAccess_003_T
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_ArrayAccess_003_T {
    @PostMapping(value = "Base_ArrayAccess_003_T")
    public Map<String, Object> aTaintCase0112(@RequestParam(defaultValue = "ls") String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String strs[] = new String[1];
            strs[0]=cmd;
            List<String> target = Lists.newArrayList("cd /","ls","ls -la");
            CollectionUtils.mergeArrayIntoCollection(strs,target);
            Runtime.getRuntime().exec(target.get(3));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
