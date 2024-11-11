package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.interface_class;

import com.sast.astbenchmark.model.alias.SimpleTree;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-AccessPathChain-Tree
 * Level 3
 * Date 2024-07-05
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->域敏感->对象属性->对象指针->AccessPathChain-Tree
// bind_url = accuracy/fieldSensitive/objectField/AccessPathChainAlias_001_T
// evaluation information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class AccessPathChainAlias_001_T {
    @PostMapping(value = "AccessPathChainAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            SimpleTree<String> myTree = new SimpleTree<>();
            myTree.left = new SimpleTree<>();
            myTree.left.right = new SimpleTree<>();
            myTree.left.left = new SimpleTree<>();
            myTree.left.right.left = myTree;
            myTree.data = cmd;
            Runtime.getRuntime().exec(myTree.left.right.left.left.right.left.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}