/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.xast.score.util;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
/**
 * @author lyh01158086@antgroup.com
 * @version BooleanExpressionEvaluatorUtil.java, v 0.1 2024年06月13日 下午2:47 lyh01158086@antgroup.com
 */
public class BooleanExpressionEvaluatorUtil {

    // 正则表达式匹配占位符形
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\b([a-zA-Z0-9_]+\\.java)\\b");

    /**
     * 计算布尔表达式的值
     *
     * @param expression 带有占位符的布尔表达式字符串
     * @param valuesMap  带有每个占位符对应布尔值的Map
     * @return 布尔计算结果
     */
    public static Boolean evaluate(String expression, Map<String, Boolean> valuesMap) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(expression);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String placeholder = matcher.group();
            Boolean value = valuesMap.get(placeholder);

            if (value == null) {
                throw new IllegalArgumentException("No value found for key: " + placeholder);
            }

            // 使用 Matcher.quoteReplacement 以确保正确替换特殊字符
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(value.toString()));
        }
        matcher.appendTail(buffer);
        expression = buffer.toString();

        // 使用 ScriptEngine 计算布尔表达式的值
        return evaluateExpression(expression);
    }

    /**
     * 使用 ScriptEngine 评估布尔表达式
     *
     * @param expression 经过替换后的布尔表达式字符串
     * @return 布尔计算结果
     */
    private static Boolean evaluateExpression(String expression) {
        ScriptEngine engine = new NashornScriptEngineFactory().getScriptEngine();
//        ScriptEngine engine = manager.getEngineByName("js");
//        System.out.println(engine);
//        System.out.println(engine.getClass().getName());

        try {
            return (Boolean) engine.eval(expression);
        } catch (ScriptException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid boolean expression", e);
        }
    }
}