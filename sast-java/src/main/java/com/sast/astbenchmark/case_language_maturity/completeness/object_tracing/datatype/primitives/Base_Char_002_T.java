package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.primitives;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Character作为污点
 * Level 2
 * Date 2024-05-09
 * 注意，case中的 SinkUtil.sink 为自定义Sink，由于评测对基础类型无法直接找到sink点，因此设计出了一个自定义Sink。
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Char
// bind_url = completeness/object_tracing/datatype/primitives/Base_Char_002_T
// evaluation information end
@RestController()
@RequestMapping("completeness/object_tracing/datatype/primitives")
public class Base_Char_002_T {
    @PostMapping("Base_Char_002_T/{cmd}")
    public Map<String, Object> aTaintCase0148(@PathVariable Character cmd) {
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
