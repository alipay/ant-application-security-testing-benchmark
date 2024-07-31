package com.iast.astbenchmark.casetool.parser;

import com.iast.astbenchmark.common.XastIastException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iast.astbenchmark.casetool.generator.XastGeneratorException;
import com.iast.astbenchmark.casetool.parser.domain.CaseJavaFileParseResult;
import com.iast.astbenchmark.casetool.parser.domain.ParseTask;
import com.iast.astbenchmark.common.utils.FileUtil;

/**
 * @author CC11001100
 */
class CaseJavaSourceCodeFileParserTest {

    @Test
    void parse() throws XastIastException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        String caseFileClassPath = "testcase/parser/AccuracyTrackTaintObject_ParamSinkSameValue_001_F.java";
        String fullClassPath =
            "com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.variable.AccuracyTrackTaintObject_ParamSinkSameValue_001_F";

        ParseTask build = ParseTask.builder().javaSourceFilePath(FileUtil.getClassPathFileAbsPath(caseFileClassPath))
            .javaFullClassName(fullClassPath).build();
        CaseJavaFileParseResult result = new CaseJavaSourceCodeFileParser().parse(build);
        System.out.println(result);
        Assertions.assertNotNull(result);

    }

}