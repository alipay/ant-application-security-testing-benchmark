package com.iast.astbenchmark.analyser.cache;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.iast.astbenchmark.cases.AstTaintCase001;
import com.iast.astbenchmark.cases.AstTaintCase002;
import com.iast.astbenchmark.cases.AstTaintCase003;
import com.iast.astbenchmark.cases.AstTaintCase004;
import com.iast.astbenchmark.cli.tree.CaseNode;
import com.iast.astbenchmark.cli.tree.CaseNodeTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.iast.astbenchmark.analyser.cache.AnnotationProcessorUtil.buildCaseMap;
import static com.iast.astbenchmark.analyser.cache.CaseStuctCache.leafData;

@Component
@Slf4j
public class CasetargeCache {
    protected static Map<String, CaseTargetBean> targetMap = Maps.newLinkedHashMap();
    protected static Map<String, CaseTargetBean> targetMap2 = Maps.newLinkedHashMap();

    @PostConstruct
    void init() {
        this.goinit();
    }

    public static void initNow() {
        new CasetargeCache().goinit();
    }

    //public static void main(String[] args) {
    //    /**
    //     * json转注解，两边结果对比
    //     * root2,json解析的root
    //     * leafData2,json解析的所有叶子节点数据
    //     */
    //     CaseNode root2;
    //    Map<String, CaseNode> leafData2 ;
    //    new CasetargeCache().initNow();
    //    String target = IoUtil.read(new ClassPathResource("config/case_target_list.json").getStream(), Charset.forName("utf-8"));
    //    JSONArray array = JSONUtil.parseArray(target);
    //    array.stream().forEach(e -> {
    //        CaseTargetBean bean = JSONUtil.toBean(JSONUtil.toJsonStr(e), CaseTargetBean.class);
    //        targetMap2.put(bean.getCaseNo(), bean);
    //    });
    //    root2=CaseNodeTreeUtil.initRoot2();
    //    leafData2=CaseNodeTreeUtil.leafMap(root2);
    //    System.out.println(leafData.size()+"/"+ leafData2.size());
    //    Set<String> keySet= new HashSet<>();
    //    keySet.addAll(leafData2.keySet())  ;
    //    keySet.addAll(leafData.keySet());
    //    for (String key : keySet) {
    //        if(!leafData.containsKey(key)){
    //            System.out.println("注解缺少"+key);
    //            continue;
    //        }
    //        if(!leafData2.containsKey(key)){
    //            System.out.println("json缺少:"+key);
    //            continue;
    //        }
    //        CaseTargetBean leaf =leafData.get(key).getLeafData();
    //        List<String> targetData=leaf.getData().stream().map(e->e.getTag()+e.getResult()).collect(Collectors.toList());
    //        CaseTargetBean leaf2 =leafData2.get(key).getLeafData();
    //        List<String> targetData2=leaf2.getData().stream().map(e->e.getTag()+e.getResult()).collect(Collectors.toList());
    //        Collections.sort(targetData);
    //        Collections.sort(targetData2);
    //        if(!targetData.equals(targetData2)){
    //            System.out.println(key);
    //        }
    //
    //    }
    //
    //}
    private void goinit() {
        if (targetMap.isEmpty()) {
            try {
                buildCaseMap(AstTaintCase001.class);
                buildCaseMap(AstTaintCase002.class);
                buildCaseMap(AstTaintCase003.class);
                buildCaseMap(AstTaintCase004.class);
                CaseStuctCache.root = CaseNodeTreeUtil.initRoot();
                CaseStuctCache.leafData=CaseNodeTreeUtil.leafMap(CaseStuctCache.root);
            } catch (Exception e) {
                log.error("ERROR : Case加载失败，请检查您的case_target_list.json:{}", e);
            }
        }
    }



    public static CaseTargetBean getTargetByCaseKey(String key) {
        return targetMap.get(key);
    }

    public static Map<String, CaseTargetBean> getAllCases() {
        return targetMap;
    }
}
