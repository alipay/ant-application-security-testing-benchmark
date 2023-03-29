package com.iast.astbenchmark.analyser.cache;

import com.iast.astbenchmark.cli.tree.CaseNode;
import com.iast.astbenchmark.cli.tree.CaseNodeTreeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class CaseStuctCache {
    private static CaseNode root;
    private static Map<String, CaseNode> leafData ;
    static {
        root = CaseNodeTreeUtil.initRoot();
        leafData=CaseNodeTreeUtil.leafMap(root);
    }

    public static CaseNode getLeafByCaseNo(String caseNo){
        try {
            return leafData.get(caseNo);
        }catch (Exception e){
            log.error("获取节点异常:{}",caseNo);
            throw  e;
        }

    }
    public static Map<String, CaseNode> getAllLeaf(){
        return leafData;
    }
    public static CaseNode getRoot(){
        return root;
    }

    public static void main(String[] args) {
        for (CaseNode value : CaseStuctCache.getAllLeaf().values()) {
            System.out.println(value.getFullName());
        }
    }
}
