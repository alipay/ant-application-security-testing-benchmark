package com.iast.astbenchmark.casetool.parser;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 *
 * 用于解析case类绑定到的URL
 *
 * @author CC11001100
 */
public class RequestMappingUrlParser {

    /**
     * 解析此case绑定到的url映射
     *
     * @param caseClass
     * @return
     */
    public String parse(Class caseClass) {

        String classMappingUrl = this.parseClassMappingUrl(caseClass);
        String methodMappingUrl = this.parseMethodMappingUrl(caseClass);

        boolean hasDelimiter = false;
        if (StringUtils.isNotBlank(classMappingUrl) && classMappingUrl.endsWith("/")) {
            hasDelimiter = true;
        }
        if (StringUtils.isNotBlank(methodMappingUrl) && methodMappingUrl.startsWith("/")) {
            hasDelimiter = true;
        }

        StringBuilder url = new StringBuilder();
        if (StringUtils.isNotBlank(classMappingUrl)) {
            url.append(classMappingUrl);
        }
        if (!hasDelimiter) {
            url.append("/");
        }
        if (StringUtils.isNotBlank(methodMappingUrl)) {
            url.append(methodMappingUrl);
        }
        return url.toString();
    }

    /**
     * @param caseClass
     * @return
     */
    private String parseClassMappingUrl(Class caseClass) {
        Annotation[] annotations = caseClass.getAnnotations();
        return this.parseMappingUrl(annotations);
    }

    /**
     * 获取case方法的映射路径，目前一个case文件中只能有一个映射方法
     *
     * @param clazz
     * @return
     */
    private String parseMethodMappingUrl(Class clazz) {
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            Annotation[] annotations = declaredMethod.getAnnotations();
            String s = this.parseMappingUrl(annotations);
            if (StringUtils.isNotBlank(s)) {
                return s;
            }
        }
        return null;
    }

    /**
     * // TODO 2024-06-18 11:43:21 配置了多个路由映射的情况，其它注释的情况，blabla
     *
     * @param annotations
     * @return
     */
    private String parseMappingUrl(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof RestController) {
                RestController restController = (RestController) annotation;
                if (StringUtils.isNotBlank(restController.value())) {
                    return restController.value();
                }
            } else if (annotation instanceof RequestMapping) {
                RequestMapping requestMapping = (RequestMapping) annotation;
                String[] value = requestMapping.value();
                if (value != null && value.length != 0 && StringUtils.isNotBlank(value[0])) {
                    return value[0];
                }
            } else if (annotation instanceof GetMapping) {
                GetMapping getMapping = (GetMapping) annotation;
                String[] value = getMapping.value();
                if (value != null && value.length != 0 && StringUtils.isNotBlank(value[0])) {
                    return value[0];
                }
            } else if (annotation instanceof PostMapping) {
                PostMapping postMapping = (PostMapping) annotation;
                String[] value = postMapping.value();
                if (value != null && value.length != 0 && StringUtils.isNotBlank(value[0])) {
                    return value[0];
                }
            } else if (annotation instanceof PutMapping) {
                PutMapping putMapping = (PutMapping) annotation;
                String[] value = putMapping.value();
                if (value != null && value.length != 0 && StringUtils.isNotBlank(value[0])) {
                    return value[0];
                }
            }
        }
        return null;
    }

}
