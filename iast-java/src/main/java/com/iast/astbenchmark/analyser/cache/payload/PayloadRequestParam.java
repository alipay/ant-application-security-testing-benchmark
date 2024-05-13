package com.iast.astbenchmark.analyser.cache.payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 用于表示触发请求时携带的param
 *
 * @author CC11001100
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PayloadRequestParam {

    /**
     * 参数的名称，比如 "cmd"
     *
     * @return
     */
    String name();

    /**
     * 参数的值，比如 "ls"
     *
     * @return
     */
    String value();

}
