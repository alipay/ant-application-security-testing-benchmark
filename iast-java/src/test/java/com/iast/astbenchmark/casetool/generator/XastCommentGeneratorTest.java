package com.iast.astbenchmark.casetool.generator;

import com.iast.astbenchmark.common.XastIastException;
import org.junit.jupiter.api.Test;

import com.iast.astbenchmark.casetool.parser.CaseJavaSourceCodeFileParser;
import com.iast.astbenchmark.casetool.parser.domain.CaseJavaFileParseResult;
import com.iast.astbenchmark.casetool.parser.domain.ParseTask;
import com.iast.astbenchmark.common.utils.FileUtil;

/**
 * @author CC11001100
 */
class XastCommentGeneratorTest {

    @Test
    void generate() throws XastIastException, ClassNotFoundException, InstantiationException,
            IllegalAccessException {

        String caseFileClassPath = "testcase/generator/AccuracyTrackTaintObject_ParamSinkSameValue_001_F.java";
        String fullClassPath =
            "com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.variable.AccuracyTrackTaintObject_ParamSinkSameValue_001_F";

        ParseTask build = ParseTask.builder().javaSourceFilePath(FileUtil.getClassPathFileAbsPath(caseFileClassPath))
            .javaFullClassName(fullClassPath).build();
        CaseJavaFileParseResult result = new CaseJavaSourceCodeFileParser().parse(build);

        XastCommentGenerator xastCommentGenerator = new XastCommentGenerator();
        GeneratorTask task = GeneratorTask.builder().caseJavaFileParseResult(result).groupCaseList(null).build();
        String newJavaCode = xastCommentGenerator.generate(task);
        System.out.println(newJavaCode);

    }

}