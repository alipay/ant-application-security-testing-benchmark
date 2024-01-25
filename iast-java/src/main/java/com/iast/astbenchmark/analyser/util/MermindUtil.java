package com.iast.astbenchmark.analyser.util;

import com.iast.astbenchmark.analyser.cache.CaseStuctCache;
import com.iast.astbenchmark.analyser.cache.CasetargeCache;
import com.iast.astbenchmark.cli.tree.CaseNode;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class MermindUtil {
    private static String mermindScript = "";

    public static void main(String[] args) {
        CasetargeCache.initNow();
        mermindScript = "```mermind\n";
        getGraph(CaseStuctCache.getRoot());
        //mermind graph脑图
        //System.out.println(mermindScript);
        //mermind 脑图,这个版本格式有点乱
        printTree(CaseStuctCache.getRoot(),CaseStuctCache.getRoot().getDeepth());
        //FileUtil.writeUtf8String("JAVA.md", mermindScript);
    }
    public static void printTree(CaseNode node, int depth) {
        if (node == null) {
            return;
        }
        // 创建缩进字符串，每个深度级别增加一个空格
        String indent = new String(new char[depth]).replace('\0', ' ');

        String name =node.getName().replaceAll("\\)","").replace("(","")
                .replaceAll("]","").replace("[","");
        // 打印当前节点的名称与缩进
        System.out.println(indent + "\""+name+ "\"");

        // 递归打印每个子节点，深度加一
        List<CaseNode> children = node.getChildren();
        if (children != null) {
            for (CaseNode child : children) {
                printTree(child, depth + 1);
            }
        }
    }

    public static String printMermindScript() {
        mermindScript = "```mermind\n";
        getGraph(CaseStuctCache.getRoot());
        return mermindScript;
    }

    private static void getGraph(CaseNode node) {
        if (node.getDeepth() == 1) {
            if (!mermindScript.contains("graph LR")) {
                mermindScript = mermindScript + "graph LR\n";
            }
            for (CaseNode child : node.getChildren()) {
                getGraph(child);
            }
            return;
        }
        if (node.getDeepth() > 1) {
            String parent = node.getParent().getId() + "[\"" + node.getParent().getName() + "\"]";
            String current = node.getId() + "[\"" + node.getName() + "\"]";

            if (!CollectionUtils.isEmpty(node.getChildren())) {
                for (CaseNode child : node.getChildren()) {
                    getGraph(child);
                }
            }
            if (node.getLeafData() != null) {
                current = node.getLeafData().getCaseNo() + "[\"" + node.getName() + "\"]";
            }
            if (!StringUtils.isEmpty(node.getName())) {
                mermindScript = mermindScript + parent + "==>" + current + "\n";
            }

        }

    }
}
