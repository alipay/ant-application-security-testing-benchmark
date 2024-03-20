package com.iast.astbenchmark.analyser.cache;

import java.util.Map;

import com.iast.astbenchmark.cli.tree.CaseNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CaseStuctCache {
    protected static CaseNode root;
    protected static Map<String, CaseNode> leafData;

    public static CaseNode getLeafByCaseNo(String caseNo) {
        try {
            return leafData.get(caseNo);
        } catch (Exception e) {
            log.error("获取节点异常:{}", caseNo);
            throw e;
        }

    }

    public static Map<String, CaseNode> getAllLeaf() {
        return leafData;
    }

    public static CaseNode getRoot() {
        return root;
    }

    // public static void main(String[] args) {
    // for (CaseNode value : CaseStuctCache.getAllLeaf().values()) {
    // System.out.println(value.getFullName());
    // }
    // }
}
