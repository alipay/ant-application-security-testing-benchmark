package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * Introduction 对象中的简单类型对象，Set对象为污点
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Set
// compose = Base_Set_001_T.java || Base_Set_002_T.java
// bind_url = completeness/base/object/javaNative/Base_Set_001_T
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Set_001_T {
    @PostMapping("Base_Set_001_T")
    public Map<String, Object> aTaintCase0143(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", "error");
            return modelMap;
        }
        Set<String> stringSet = new HashSet<>(cmd);
        SinkUtil.sink(stringSet);
        modelMap.put("status", "success");
        return modelMap;
    }
}
