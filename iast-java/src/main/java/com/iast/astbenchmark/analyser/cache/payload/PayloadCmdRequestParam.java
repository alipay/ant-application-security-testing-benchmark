package com.iast.astbenchmark.analyser.cache.payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 发送请求时携带参数 "cmd=ls"
 *
 * @author CC11001100
 */
@PayloadRequestParam(name = "cmd", value = "ls")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PayloadCmdRequestParam {

    String value() default "ls";

}
