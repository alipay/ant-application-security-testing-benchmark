package com.iast.astbenchmark.analyser.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于描述一个测试case相关的信息
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface IastTestCase {

    /**
     * Case编码
     *
     * @return
     */
    String caseNo();

    /**
     * 用于简介介绍测试case的作用
     *
     * @return
     */
    String description() default "";

    /**
     * case 全名包括路径
     *
     * @return
     */
    @Deprecated
    String caseFullName();

    ///**
    // * 测试用例所属的分类
    // *
    // * @return
    // */
    //String[] category();

    /**
     * 这个方法期望检出漏洞的结果,true为期待检出,false为不期待检出
     *
     * @return
     */
    boolean hasVul();

    /**
     * 这个方法的标识，可以用于日志等检出结果中检索
     *
     * @return
     */
    String thisMethodTag();

}