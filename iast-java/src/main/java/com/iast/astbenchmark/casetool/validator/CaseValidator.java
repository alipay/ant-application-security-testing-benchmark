package com.iast.astbenchmark.casetool.validator;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.casetool.parser.domain.CaseJavaFileParseResult;

/**
 *
 * 用于校验case文件是否合法
 *
 * @author CC11001100
 */
public class CaseValidator {

    /**
     * 检查出来的错误信息
     */
    private List<String> errorMsgList = new ArrayList<>();

    /**
     * 只是警告级别的信息
     */
    private List<String> warnMsgList = new ArrayList<>();

    public void parse(List<CaseJavaFileParseResult> list) {

    }

    private void validate(CaseJavaFileParseResult caseJavaFileParseResult) {

        Class caseClass = caseJavaFileParseResult.getCaseClass();

        // case类上必须要有url映射，以便能够触发
        Annotation annotation = caseClass.getAnnotation(RestController.class);
        if (annotation == null) {
            
        }

    }

}
