package com.iast.astbenchmark.analyser.cache;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.iast.astbenchmark.analyser.bean.CaseTargetItemBean;
import com.iast.astbenchmark.analyser.bean.consts.CaseTypeEnum;

public final class AnnotationProcessorUtil {

    /**
     * 处理类注解 test method
     * 
     * @param clazz 待处理的类
     */
    public static void processAnnotations(Class<?> clazz) {
        // 处理方法注解

        // 处理方法注解
        for (Method method : clazz.getDeclaredMethods()) {
            // 判断方法是否有@CaseTag注解
            if (method.isAnnotationPresent(CaseTag.class)) {
                // 获取方法上的@CaseTag注解
                CaseTag methodAnnotation = method.getAnnotation(CaseTag.class);
                // 打印方法名和注解值
                System.out
                    .println("Method " + method.getName() + " has annotation with value: " + methodAnnotation.caseNo());
            }
        }
    }

    /**
     * 构建用例映射
     *
     * @param clazz 待处理的类
     */
    public static void buildCaseMap(Class<?> clazz) {
        // 遍历类中的所有方法
        for (Method method : clazz.getDeclaredMethods()) {
            // 判断方法是否被@CaseTag注解标记
            if (method.isAnnotationPresent(CaseTag.class)) {
                // 获取@CaseTag注解实例
                CaseTag methodAnnotation = method.getAnnotation(CaseTag.class);
                // 获取用例编号
                String caseNo = methodAnnotation.caseNo();
                // 判断用例编号对应的目标对象是否已存在
                if (!CasetargeCache.targetMap.containsKey(caseNo)) {
                    // 如果不存在，则构建新的目标对象并添加到缓存中
                    CasetargeCache.targetMap.put(caseNo, buildTargetBean(methodAnnotation));
                } else {
                    // 如果已存在，则修改目标对象并替换缓存中的对象
                    CaseTargetBean modifyBean =
                        modifyTargetBean(CasetargeCache.targetMap.get(caseNo), methodAnnotation);
                    CasetargeCache.targetMap.replace(caseNo, modifyBean);
                }
            }
        }
    }

    /**
     * 构建用例目标 bean
     *
     * @param methodAnnotation 方法注解
     * @return 用例目标 bean
     */
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

    /**
     * 修改用例目标 bean
     *
     * @param caseTargetBean 原始的用例目标 bean
     * @param methodAnnotation 方法注解，包含用例标签和预期结果
     * @return 修改后的用例目标 bean，包含新的用例标签和预期结果
     */
    private static CaseTargetBean modifyTargetBean(CaseTargetBean caseTargetBean, CaseTag methodAnnotation) {
        // 获取方法注解中的用例标签和预期结果
        String thisMethodTag = methodAnnotation.thisMethodTag();
        boolean result = methodAnnotation.thisMethodExpectedResult();
        // 创建一个新的用例目标项 bean，设置用例标签和预期结果
        CaseTargetItemBean itemBean = new CaseTargetItemBean();
        itemBean.setTag(thisMethodTag);
        itemBean.setResult(result);
        // 获取原始用例目标 bean 中的用例目标项列表
        List<CaseTargetItemBean> itemBeans = caseTargetBean.getData();
        // 如果用例目标项列表为空，则直接将新的用例目标项添加到列表中
        if (CollectionUtils.isEmpty(itemBeans)) {
            itemBeans.add(itemBean);
        } else {
            // 如果用例目标项列表不为空，则判断是否已存在相同标签的用例目标项
            boolean itemExist = false;
            for (CaseTargetItemBean item : itemBeans) {
                if (item.getTag().equalsIgnoreCase(thisMethodTag)) {
                    itemExist = true;
                    break;
                }
            }
            // 如果不存在相同标签的用例目标项，则将新的用例目标项添加到列表中
            if (!itemExist) {
                itemBeans.add(itemBean);
            }
        }

        // 将更新后的用例目标项列表设置回用例目标 bean 中，并返回修改后的用例目标 bean
        caseTargetBean.setData(itemBeans);
        return caseTargetBean;
    }

}
