package com.iast.astbenchmark.analyser.cache.payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 *
 * 用于表示
 *
 * @author CC11001100
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Payload {

    /**
     * 请求体
     *
     * @return
     */
    String body();

}
