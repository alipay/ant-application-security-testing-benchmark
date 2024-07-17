package com.iast.astbenchmark.analyser.cache;

/**
 *
 * 如果某个case被标记为废弃了，则使用这个注解标记它
 *
 * @author CC11001100
 */
public @interface CaseDeprecated {

    /**
     * 从什么时候被标记为废弃的
     *
     * @return
     */
    String since();

    /**
     * 被标记为废弃的原因是什么
     *
     * @return
     */
    String cause();

}
