package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

import com.sast.astbenchmark.model.alias.DataClass;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度-链路跟踪完整度-引用和指针-别名-对象属性-对象指针-NullAlias
 * Level 3
 * Date 2024-07-02
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->引用和指针->别名->对象属性->对象指针->NullAlias
// bind_url = completeness/chain_tracing/references_pointers/alias/NullAlias_001_T
// evaluation information end
@RestController
@RequestMapping("completeness/chain_tracing/references_pointers/alias")
public class NullAlias_001_T {
    @PostMapping(value = "NullAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            DataClass dc = new DataClass();
            DataClass dc2 = null;
            DataClass dc3 = new DataClass();

            dc2 = dc3;

            dc2.next = dc;
            dc2 = null;

            String a = cmd;
            dc.data = a;
            dc = null;

            Runtime.getRuntime().exec(dc3.next.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
