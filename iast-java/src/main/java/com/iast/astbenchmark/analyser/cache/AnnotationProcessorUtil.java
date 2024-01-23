package com.iast.astbenchmark.analyser.cache;

import com.google.common.collect.Lists;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.iast.astbenchmark.analyser.bean.CaseTargetItemBean;
import com.iast.astbenchmark.analyser.bean.consts.CaseTypeEnum;

import java.lang.reflect.Method;
import java.util.List;

public class AnnotationProcessorUtil {
    public static void processAnnotations(Class<?> clazz) {
        // 处理方法注解
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CaseTag.class)) {
                CaseTag methodAnnotation = method.getAnnotation(CaseTag.class);
                System.out.println("Method " + method.getName() + " has annotation with value: " + methodAnnotation.caseNo());
            }
        }
    }

    public static void buildCaseMap(Class<?> clazz) {
        // 处理方法注解
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CaseTag.class)) {
                CaseTag methodAnnotation = method.getAnnotation(CaseTag.class);
                String caseNo = methodAnnotation.caseNo();
                if (!CasetargeCache.targetMap.containsKey(caseNo)) {
                    CasetargeCache.targetMap.put(caseNo, buildTargetBean(methodAnnotation));
                } else {
                    CaseTargetBean modifyBean = modifyTargetBean(CasetargeCache.targetMap.get(caseNo), methodAnnotation);
                    CasetargeCache.targetMap.replace(caseNo, modifyBean);
                }
            }
        }
    }

    private static CaseTargetBean buildTargetBean(CaseTag methodAnnotation) {
        String caseNo = methodAnnotation.caseNo();
        String caseFullName = methodAnnotation.caseFullName();
        String thisMethodTag = methodAnnotation.thisMethodTag();
        boolean result = methodAnnotation.thisMethodExpectedResult();
        CaseTargetBean targetBean = new CaseTargetBean();
        targetBean.setCaseNo(caseNo);
        CaseTargetItemBean itemBean = new CaseTargetItemBean();
        itemBean.setTag(thisMethodTag);
        itemBean.setResult(result);
        List<CaseTargetItemBean> item = Lists.newArrayList();
        item.add(itemBean);
        targetBean.setCaseDesc(caseFullName);
        targetBean.setData(item);
        targetBean.setCaseType(CaseTypeEnum.getDescByTag(caseFullName));
        targetBean.setWeight(1);
        return targetBean;
    }

    private static CaseTargetBean modifyTargetBean(CaseTargetBean caseTargetBean, CaseTag methodAnnotation) {
        String thisMethodTag = methodAnnotation.thisMethodTag();
        boolean result = methodAnnotation.thisMethodExpectedResult();
        CaseTargetItemBean itemBean = new CaseTargetItemBean();
        itemBean.setTag(thisMethodTag);
        itemBean.setResult(result);
        List<CaseTargetItemBean> item = caseTargetBean.getData();
        item.add(itemBean);
        caseTargetBean.setData(item);
        return caseTargetBean;
    }

}
