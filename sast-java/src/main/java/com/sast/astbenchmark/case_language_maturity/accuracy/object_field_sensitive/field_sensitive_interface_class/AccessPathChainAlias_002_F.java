package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive_interface_class;

import com.sast.astbenchmark.model.alias.SimpleTree;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item =  准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
// scene introduction = 对象指针->AccessPathChain-Tree
// level = 3
// bind_url = accuracy/object_field_sensitive/field_sensitive_interface_class/AccessPathChainAlias_002_F
// evaluation information end
@RestController
@RequestMapping("accuracy/object_field_sensitive/field_sensitive_interface_class")
public class AccessPathChainAlias_002_F {
    @PostMapping(value = "AccessPathChainAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            SimpleTree<String> myTree = new SimpleTree<>();
            myTree.left = new SimpleTree<>();
            myTree.left.right = new SimpleTree<>();
            myTree.left.left = new SimpleTree<>();
            myTree.left.right.left = myTree;
            myTree.data = cmd;
            Runtime.getRuntime().exec(myTree.left.right.left.left.left.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}