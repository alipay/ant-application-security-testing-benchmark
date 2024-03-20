package com.iast.astbenchmark.analyser.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 定义注解可以应用于方法
@Retention(RetentionPolicy.RUNTIME) // 定义注解在运行时可用
public @interface CaseTag {
    /**
     * Case编码
     *
     * @return
     */
    String caseNo();

    /**
     * case 全名包括路径
     *
     * @return
     */
    String caseFullName();

    /**
     * 这个方法期望检出漏洞的结果,true为期待检出,false为不期待检出
     *
     * @return
     */
    boolean thisMethodExpectedResult();

    /**
     * 这个方法的标识，可以用于日志等检出结果中检索
     *
     * @return
     */
    String thisMethodTag();
}