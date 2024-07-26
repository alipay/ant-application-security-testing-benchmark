package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.SimpleTree;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-AccessPathChain-Tree
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->AccessPathChain-Tree
// compose = AccessPathChainAlias_001_T.java && !AccessPathChainAlias_002_F.java
// bind_url = accuracy/objectSensitive/AccessPathChainAlias_002_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
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