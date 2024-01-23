package com.iast.astbenchmark.cli.tree;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.iast.astbenchmark.analyser.cache.CasetargeCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Slf4j
public class CaseNodeTreeUtil {
    public static void main(String[] args) {
        System.out.println(CaseNodeTreeUtil.initRoot());
    }

    public static CaseNode initRoot() {
        BufferedReader reader =null;
        InputStream inputStream  =null;
        try {
            inputStream = CaseNodeTreeUtil.class.getClassLoader().getResourceAsStream("config/caseNodeTree.txt");
            reader= new BufferedReader(new InputStreamReader(inputStream));
            List<String> lines =Lists.newArrayList();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            //= FileUtil.readLines("config/caseNodeTree.txt", Charset.forName("utf-8"));
            CasetargeCache.initNow();
            CaseNode root = CaseNode.builder()
                    .type(CaseNodeType.ROOT)
                    .id(0)
                    .deepth(1)
                    .name("IAST引擎能力评估体系(JAVA)")
                    .build();

            for (int row = 0; row < lines.size(); row++) {
                if (StrUtil.isEmpty(lines.get(row))||lines.get(row).startsWith("#")) {
                    continue;
                }
                String[] nodesData = lines.get(row).split("->");
                addTreeNode(root, 0, row + 1, nodesData);
            }
            getGraph(root);
            return root;
        }catch (Exception e){
            log.error("初始化异常:{}",e);
        }finally {
            try {
                if(reader!=null){
                    reader.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
            }catch (IOException e){

            }

        }
       return null;
    }

    private static void getGraph(CaseNode node){
        if(node.getDeepth()==1){
            System.out.println("graph LR");
            for (CaseNode child : node.getChildren()) {
                getGraph(child);
            }
            return;
        }
        //if(node.getLeafData()!=null){
        //    System.out.println(node.getName()+"-->"+node.getLeafData().getCaseNo()+"\n");
        //    return ;
        //}
        if(node.getDeepth()>1){
            //String parent ="N"+node.getParent().getId()+"[\"`**"+node.getParent().getName()+"**`\"]";
            //String current ="N"+node.getId()+"[\"`**"+node.getName()+"**`\"]";
            String parent ="N"+node.getParent().getId()+"[\""+node.getParent().getName()+"\"]";
            String current ="N"+node.getId()+"[\""+node.getName()+"\"]";
            if(!StringUtils.isEmpty(node.getName())){
                System.out.println(parent+"==>"+current);
            }

            if(!CollectionUtils.isEmpty(node.getChildren())){
                for (CaseNode child : node.getChildren()) {
                    getGraph(child);
                }
            }
        }

    }

    public static Map<String, CaseNode> leafMap(CaseNode root) {
        Map<String, CaseNode> leafMap =Maps.newLinkedHashMap();
        findLeaf(leafMap, root);
        return leafMap;
    }

    private static void findLeaf(Map<String, CaseNode> leafMap, CaseNode parent) {
        if (parent.getType().equals(CaseNodeType.LEAF)) {
            CaseNode leaf = parent;
            if(leaf.getLeafData()!=null&&StrUtil.isNotEmpty(leaf.getLeafData().getCaseNo())&&leaf!=null){
                leafMap.put(leaf.getLeafData().getCaseNo(), leaf);
            }else {
                System.out.println(leaf.getName());
            }
        } else {
            for (CaseNode child : parent.getChildren()) {
                findLeaf(leafMap, child);
            }
        }
    }

//    public static void main(String[] args) {
//        List<String> lines = FileUtil.readLines("data/caseNodeTree.txt", Charset.forName("utf-8"));
//        List<String> linesNew = Lists.newArrayList();
//        for (int i = 0; i <= 46; i++) {
//            linesNew.add(lines.get(i)+"@aTaintCase00"+(i+1));
//        }
//        linesNew.add(lines.get(47)+"@aTaintCase00139");
//        for (int i = 48; i <= 72; i++) {
//            linesNew.add(lines.get(i)+"@aTaintCase00"+(i));
//        }
//        linesNew.add(lines.get(73)+"@aTaintCase00140");
//        for (int i = 74; i <= 104; i++) {
//            linesNew.add(lines.get(i)+"@aTaintCase00"+(i-1));
//        }
//        linesNew.add(lines.get(105)+"@aTaintCase00141");
//        linesNew.add(lines.get(106)+"@aTaintCase00103_2");
//        for (int i = 107; i <= 129; i++) {
//            linesNew.add(lines.get(i)+"@aTaintCase00"+(i-2));
//        }
//        File file = FileUtil.file("data/caseNodeTree_bak.txt");
//        FileUtil.writeUtf8Lines(linesNew,file);
//
//    }

    private static void addTreeNode(CaseNode parent, Integer deepth, Integer row, String[] nodesData) {
        deepth = deepth + 1;
        CaseNodeType type = CaseNodeType.NODE;
        Integer id = Integer.valueOf("" + String.valueOf(deepth) + String.valueOf(row));
        if (nodesData.length  <= deepth) {
            type = CaseNodeType.LEAF;

        }
        String name = nodesData[deepth-1];
        List<CaseNode> children = parent.getChildren();
        if (CollectionUtils.isEmpty(children)) {
            children = Lists.newArrayList();
            parent.setChildren(children);
        }
        CaseNode currentNode = nodeExit(children, name);
        if (currentNode == null) {
            currentNode = buildNode(type, id, deepth, name, parent);
            children.add(currentNode);
        }
        if (type.equals(CaseNodeType.NODE)) {
            addTreeNode(currentNode, deepth, row, nodesData);
        }
    }

    private static CaseNode buildNode(CaseNodeType type, Integer id, Integer deepth, String name, CaseNode parent) {
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
}



