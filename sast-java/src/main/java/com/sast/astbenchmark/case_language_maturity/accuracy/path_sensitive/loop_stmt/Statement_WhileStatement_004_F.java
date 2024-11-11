package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_stmt;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-while语句
 * Level 4
 * Date 2024-11-10
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->循环语句->while
// bind_url = accuracy/path_sensitive/loop_stmt/Statement_WhileStatement_004_F/{type}/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/loop_stmt")
public class Statement_WhileStatement_004_F {
    @GetMapping("Statement_WhileStatement_004_F/{type}/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd, @PathVariable String type) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";;
            while(StringUtils.equals(type,"ls")) {
                a = " "+ cmd;
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
