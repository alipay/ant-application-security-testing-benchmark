package com.iast.astbenchmark.cli.tree;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.iast.astbenchmark.analyser.cache.CaseStuctCache;
import com.iast.astbenchmark.analyser.cache.CasetargeCache;
import com.iast.astbenchmark.analyser.util.MermindUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.iast.astbenchmark.analyser.util.MermindUtil.mermindScript;

@Slf4j
public class CaseNodeTreeUtil {
    public static CaseNode initRoot() {
        try {
          //  CasetargeCache.initNow();
            List<String> lines =
                    CasetargeCache.getAllCases().values().stream()
                            .map(e -> e.getCaseDesc() + "@" + e.getCaseNo()).collect(Collectors.toList());

            CaseNode root = CaseNode.builder()
                    .type(CaseNodeType.ROOT)
                    .id("ROOT")
                    .deepth(1)
                    .name("IAST引擎能力评估体系(JAVA)")
                    .build();

            for (int row = 0; row < lines.size(); row++) {
                if (StrUtil.isEmpty(lines.get(row)) || lines.get(row).startsWith("#")) {
                    continue;
                }
                String[] nodesData = lines.get(row).split("->");
                addTreeNode(root, 0, row + 1, nodesData);
            }
            return root;
        } catch (Exception e) {
            log.error("初始化异常:{}", e);
        }
        return null;

    }

    public static CaseNode initRoot2() {
        BufferedReader reader = null;
        InputStream inputStream = null;
        try {
            inputStream = CaseNodeTreeUtil.class.getClassLoader().getResourceAsStream("config/sast-java评价体系.md");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> lines = Lists.newArrayList();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            //= FileUtil.readLines("config/caseNodeTree.txt", Charset.forName("utf-8"));
            CasetargeCache.initNow();
            CaseNode root = CaseNode.builder()
                    .type(CaseNodeType.ROOT)
                    .id("ROOT")
                    .deepth(1)
                    .name("IAST引擎能力评估体系(JAVA)")
                    .build();

            for (int row = 0; row < lines.size(); row++) {
                if (StrUtil.isEmpty(lines.get(row)) || lines.get(row).startsWith("#")) {
                    continue;
                }
                String[] nodesData = lines.get(row).split("->");
                addTreeNode(root, 0, row + 1, nodesData);
            }
            return root;
        } catch (Exception e) {
            log.error("初始化异常:{}", e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {

            }

        }
        return null;
    }

    ////将sast,dast,nodjs的评价体系转成mermind格式
    //public static void main(String[] args) {
    //    CaseNode root = CaseNodeTreeUtil.initRoot2();
    //    mermindScript = "```mermind\n";
    //    MermindUtil.getGraph(root);
    //    //mermind graph脑图
    //    System.out.println(mermindScript);
    //}

    public static Map<String, CaseNode> leafMap(CaseNode root) {
        Map<String, CaseNode> leafMap = Maps.newLinkedHashMap();
        findLeaf(leafMap, root);
        return leafMap;
    }

    private static void findLeaf(Map<String, CaseNode> leafMap, CaseNode parent) {
        if (parent.getType().equals(CaseNodeType.LEAF)) {
            CaseNode leaf = parent;
            if (leaf.getLeafData() != null && StrUtil.isNotEmpty(leaf.getLeafData().getCaseNo()) && leaf != null) {
                leafMap.put(leaf.getLeafData().getCaseNo(), leaf);
            } else {
                System.out.println(leaf.getName());
            }
        } else {
            for (CaseNode child : parent.getChildren()) {
                findLeaf(leafMap, child);
            }
        }
    }

    /**
     * 添加树节点
     *
     * @param parent    父节点
     * @param deepth    深度
     * @param row       行数
     * @param nodesData 节点数据
     */
    private static void addTreeNode(CaseNode parent, Integer deepth, Integer row, String[] nodesData) {
        deepth = deepth + 1;
        // 默认节点类型为NODE
        CaseNodeType type = CaseNodeType.NODE;
        // 节点id
        String id = "L" + deepth +"H"+ row;
        // 判断节点类型
        if (nodesData.length <= deepth) {
            type = CaseNodeType.LEAF;
        }
        String name = nodesData[deepth - 1];
        // 获取父节点的子节点列表
        List<CaseNode> children = parent.getChildren();
        // 如果子节点列表为空，则初始化一个新的子节点列表
        if (CollectionUtils.isEmpty(children)) {
            children = Lists.newArrayList();
            parent.setChildren(children);
        }
        CaseNode currentNode = nodeExit(children, name);
        // 判断子节点是否存在
        if (currentNode == null) {
            currentNode = buildNode(type, id, deepth, name, parent);
            children.add(currentNode);
        }
        // 递归添加子节点
        if (type.equals(CaseNodeType.NODE)) {
            addTreeNode(currentNode, deepth, row, nodesData);
        }
    }

    private static CaseNode buildNode(CaseNodeType type, String id, Integer deepth, String name, CaseNode parent) {
        CaseNode node = CaseNode.builder()
                .type(type)
                .id(id)
                .deepth(deepth)
                .name(name)
                .parent(parent)
                .build();
        if (type.equals(CaseNodeType.LEAF)) {
            String split = "@";
            if (name.contains(split)) {
                String leafName = name.split(split)[0];
                String caseId = name.split(split)[1];
                CaseTargetBean targetBean = CasetargeCache.getTargetByCaseKey(caseId);
                node.setLeafData(targetBean);
                node.setName(leafName);
            }
        }
        return node;
    }

    private static CaseNode nodeExit(List<CaseNode> caseNodes, String name) {
        for (CaseNode caseNode : caseNodes) {
            if (name.equalsIgnoreCase(caseNode.getName())) {
                return caseNode;
            }
        }
        return null;
    }

    //public static void main(String[] args) {
    //    BufferedReader reader = null;
    //    InputStream inputStream = null;
    //    try {
    //        inputStream = CaseNodeTreeUtil.class.getClassLoader().getResourceAsStream("config/caseNodeTree.txt");
    //        reader = new BufferedReader(new InputStreamReader(inputStream));
    //        List<String> lines = Lists.newArrayList();
    //        String line;
    //        while ((line = reader.readLine()) != null) {
    //            if (StrUtil.isNotEmpty(line) && line.contains("@")) {
    //                String[] tags = line.split("@");
    //                Arrays.asList(tags);
    //                String caseNo = tags[1];
    //                String caseFullName = tags[0];
    //                System.out.println("    @CaseTag(\n"
    //                        + "            caseNo =\"" + caseNo + "\",\n"
    //                        + "            caseFullName = \"" + caseFullName + "\",\n"
    //                        + "            thisMethodTag = \"" + caseNo + "\",\n"
    //                        + "            thisMethodExpectedResult = true\n"
    //                        + "    )");
    //            }
    //            System.out.println();
    //
    //        }
    //
    //    } catch (Exception e) {
    //        log.error("初始化异常:{}", e);
    //    } finally {
    //        try {
    //            if (reader != null) {
    //                reader.close();
    //            }
    //            if (inputStream != null) {
    //                inputStream.close();
    //            }
    //        } catch (IOException e) {
    //
    //        }
    //
    //    }
    //}
}



