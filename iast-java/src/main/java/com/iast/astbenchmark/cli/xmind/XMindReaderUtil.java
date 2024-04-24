package com.iast.astbenchmark.cli.xmind;

import com.google.common.collect.Lists;
import com.iast.astbenchmark.analyser.util.MermindUtil;
import com.iast.astbenchmark.cli.tree.CaseNode;
import com.iast.astbenchmark.cli.tree.CaseNodeType;
import com.iast.astbenchmark.cli.xmind.reader.XmindParser;
import com.iast.astbenchmark.cli.xmind.reader.bean.Attached;
import com.iast.astbenchmark.cli.xmind.reader.bean.JsonRootBean;
import org.apache.commons.compress.archivers.ArchiveException;
import org.dom4j.DocumentException;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class XMindReaderUtil {

    public static void main(String[] args) {
        // 请替换为您的XMind文件路径
        String filePath = "/Users/curry/Downloads/test.xmind";
        try {
            System.out.println(convertXmindToMermind(filePath));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (ArchiveException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ConcurrentHashMap<Integer,Integer> hashMap = new ConcurrentHashMap<>();
    public static String convertXmindToMermind(String path) throws DocumentException, IOException, ArchiveException {
        JsonRootBean res = XmindParser.parseObject(path);
        CaseNode root = CaseNode.builder()
                .id("L1_1")
                .name(res.getTitle())
                .deepth(1)
                .type(CaseNodeType.ROOT)
                .build();
        loopConvert(root, 1, res.getRootTopic());
        return MermindUtil.printMermindScript(root);
    }

    private static void loopConvert(CaseNode parent, int deepth, Attached nodeData) {
        deepth = deepth + 1;
        if(!hashMap.containsKey(deepth)){
            hashMap.put(deepth,0);
        }
        if (nodeData.getChildren() == null|| CollectionUtils.isEmpty(nodeData.getChildren().getAttached())) {
            return;
        } else {
            for (int i = 1; i < nodeData.getChildren().getAttached().size() + 1; i++) {
                Attached nodeDataChild = nodeData.getChildren().getAttached().get(i - 1);
                int rowNum = hashMap.get(deepth)+1;
                hashMap.put(deepth,rowNum);
                String id = "L" + deepth + "_" + rowNum;
                CaseNodeType type = CaseNodeType.NODE;
                if (nodeData.getChildren() == null) {
                    type = CaseNodeType.LEAF;
                }
                CaseNode child = CaseNode.builder().id(id).deepth(deepth).name(nodeDataChild.getTitle())
                        .type(type).parent(parent).build();
                List<CaseNode> childList = parent.getChildren() == null ? Lists.newArrayList() : parent.getChildren();
                childList.add(child);
                parent.setChildren(childList);
                loopConvert(child, deepth, nodeDataChild);
            }
        }
    }
}