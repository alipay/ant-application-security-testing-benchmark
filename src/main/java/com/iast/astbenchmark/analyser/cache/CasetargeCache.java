package com.iast.astbenchmark.analyser.cache;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Map;

@Component
@Slf4j
public class CasetargeCache {
    private static Map<String, CaseTargetBean> targetMap = Maps.newLinkedHashMap();

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
                String target = IoUtil.read(new ClassPathResource("case_target_list.json").getStream(),Charset.forName("utf-8"));
                //JSONArray array = JSONUtil.readJSONArray(FileUtil.file("case_target_list.json"), Charset.forName("utf-8"));
                JSONArray array =JSONUtil.parseArray(target);
                array.stream().forEach(e -> {
                    CaseTargetBean bean = JSONUtil.toBean(JSONUtil.toJsonStr(e), CaseTargetBean.class);
                    targetMap.put(bean.getCaseNo(), bean);
                });

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
