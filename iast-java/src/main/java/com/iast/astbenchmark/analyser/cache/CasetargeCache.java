package com.iast.astbenchmark.analyser.cache;

import com.google.common.collect.Maps;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.iast.astbenchmark.cases.AstTaintCase001;
import com.iast.astbenchmark.cases.AstTaintCase002;
import com.iast.astbenchmark.cases.AstTaintCase003;
import com.iast.astbenchmark.cases.AstTaintCase004;
import com.iast.astbenchmark.cli.tree.CaseNodeTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

import static com.iast.astbenchmark.analyser.cache.AnnotationProcessorUtil.buildCaseMap;

@Component
@Slf4j
public class CasetargeCache {
    protected static Map<String, CaseTargetBean> targetMap = Maps.newLinkedHashMap();

    @PostConstruct
    void init() {
        this.goinit();
    }

    public static void initNow() {
        new CasetargeCache().goinit();
    }

    private void goinit() {
        if (targetMap.isEmpty()) {
            try {
                //String target = IoUtil.read(new ClassPathResource("config/case_target_list.json").getStream(),Charset.forName("utf-8"));
                ////JSONArray array = JSONUtil.readJSONArray(FileUtil.file("case_target_list.json"), Charset.forName("utf-8"));
                //JSONArray array =JSONUtil.parseArray(target);
                //array.stream().forEach(e -> {
                //    CaseTargetBean bean = JSONUtil.toBean(JSONUtil.toJsonStr(e), CaseTargetBean.class);
                //    targetMap.put(bean.getCaseNo(), bean);
                //});
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

    //public static void main(String[] args) {
    //    String target = IoUtil.read(new ClassPathResource("config/case_target_list.json").getStream(),Charset.forName("utf-8"));
    //    //JSONArray array = JSONUtil.readJSONArray(FileUtil.file("case_target_list.json"), Charset.forName("utf-8"));
    //    JSONArray array =JSONUtil.parseArray(target);
    //    array.stream().forEach(e -> {
    //        CaseTargetBean bean = JSONUtil.toBean(JSONUtil.toJsonStr(e), CaseTargetBean.class);
    //        targetMap.put(bean.getCaseNo(), bean);
    //    });
    //    targetMap.forEach((k,v)-> System.out.println(k+"____"+v.getCaseDesc()));
    //}

    public static CaseTargetBean getTargetByCaseKey(String key) {
        return targetMap.get(key);
    }

    public static Map<String, CaseTargetBean> getAllCases() {
        return targetMap;
    }
}
