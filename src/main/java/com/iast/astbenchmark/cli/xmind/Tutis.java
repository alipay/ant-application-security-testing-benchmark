package com.iast.astbenchmark.cli.xmind;

import cn.hutool.system.SystemUtil;
import com.iast.astbenchmark.analyser.bean.CaseResultbean;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.iast.astbenchmark.cli.tree.CaseNode;
import com.iast.astbenchmark.cli.tree.CaseNodeType;
import org.springframework.util.CollectionUtils;
import org.xmind.core.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * 生成思维脑图工具类
 *
 * @date 2020/10/16
 */
public class Tutis {
    /**
     * 当前类路径
     */
    /**
     * 文件分隔符
     */
    public static final String FILE_SEPARATOR = SystemUtil.getOsInfo().getFileSeparator();
    public static final String CLASS_PATH = System.getProperty("user.dir") + "/results";

    /**
     * 生成思维脑图
     *
     * @param
     * @param fileName 文件名.xmind
     * @throws IOException
     * @throws CoreException
     */
    public String exportXmind(CaseNode root, Map<String, CaseResultbean> resultbeanMap, String fileName) throws IOException, CoreException {
        // 创建思维导图的工作空间
        IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
        IWorkbook workbook = workbookBuilder.createWorkbook();

        // 获得默认sheet
        ISheet primarySheet = workbook.getPrimarySheet();

        // 获得根主题
        ITopic rootTopic = primarySheet.getRootTopic();

        // 章节 topic 的列表
        //ArrayList<ITopic> chapterTopics = Lists.newArrayList();
        recycle(rootTopic, workbook, root, resultbeanMap);

        // 把章节节点添加到要节点上
        // chapterTopics.forEach(it -> rootTopic.add(it, ITopic.ATTACHED));

        // 保存
        System.out.println("路径--------------:" + CLASS_PATH);
        String path = CLASS_PATH + FILE_SEPARATOR + fileName + ".xmind";
        workbook.save(path);
        return path;

    }

    /**
     * 思维导图递归调用
     *
     * @param
     * @param workbook
     * @param
     */
    public void recycle(ITopic currentTopic, IWorkbook workbook, CaseNode currentNode, Map<String, CaseResultbean> resultbeanMap) {
        if (currentNode.getType().equals(CaseNodeType.LEAF)) {
            // 创建小节节点
            ITopic topic = workbook.createTopic();
            String leafData = leafData(currentNode, resultbeanMap);
            topic.setTitleText(leafData);
            currentTopic.add(topic,ITopic.ATTACHED);
            //chapterTopics.get(chapterTopics.size() - 1).add(topic, ITopic.ATTACHED);
        } else if (currentNode.getType().equals(CaseNodeType.NODE) ||currentNode.getType().equals(CaseNodeType.ROOT) ) {
            ITopic topic = workbook.createTopic();
            topic.setTitleText(currentNode.getName());
            currentTopic.add(topic, ITopic.ATTACHED);

            if(!CollectionUtils.isEmpty(currentNode.getChildren())){
                for (CaseNode children : currentNode.getChildren()) {
                    // if (children != null && children.getChildren() != null && children.getChildren().size() > 0) {
                    //List<CaseNode> list = children.getChildren();
                    //如果还有子节点，那么采用地柜调用继续生成
//                    if (!CollectionUtils.isEmpty(list)) {
//                        for (CaseNode node : list) {
                            recycle(topic, workbook, children, resultbeanMap);
                        //}
                    }
                    // }
                }
        }else {
            for (CaseNode children : currentNode.getChildren()) {
                if (children != null) {
                    if (children.getChildren() != null && children.getChildren().size() > 0) {
                        // 创建章节节点
                        ITopic topic = workbook.createTopic();
                        topic.setTitleText(children.getName());
                        currentTopic.add(topic, ITopic.ATTACHED);
                        List<CaseNode> list = children.getChildren();
                        //如果还有子节点，那么采用地柜调用继续生成
                        if (!CollectionUtils.isEmpty(list)) {
                            for (CaseNode node : list) {
                                recycle(topic, workbook, node, resultbeanMap);
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }

}

    private String leafData(CaseNode leafNode, Map<String, CaseResultbean> resultbeanMap) {

        CaseTargetBean targetBean = leafNode.getLeafData();
        String caseNo = targetBean.getCaseNo();
        CaseResultbean resultbean = resultbeanMap.get(caseNo);
        if (resultbean == null) {
            return leafNode.getName();
        } else {
            return leafNode.getName() + ": " + resultbean.getResult().getDesc();
        }
    }
//    public static void main(String[] args) {
//        Tutis tutis = new Tutis();
//        try {
//            tutis.exportXmind(CaseStuctCache.getRoot(),"测试思维导出");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (CoreException e) {
//            throw new RuntimeException(e);
//        }
//    }

}

