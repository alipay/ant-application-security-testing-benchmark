package com.iast.astbenchmark.casetool.parser.comment;

import com.iast.astbenchmark.casetool.parser.XastCaseParserException;
import com.iast.astbenchmark.casetool.parser.domain.XastMultiLineCommentParseResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author CC11001100
 */
class XastMultiLineCommentParserTest {

    @Test
    void parse() throws XastCaseParserException {

        // 没有注释的内容
        {
            String caseFilePath =
                    "testcase/parser/multiLineCommentParser/AccuracyTrackTaintObject_ParamSinkSameValue_001_F.java";
            String javaSourceCode = com.iast.astbenchmark.common.utils.FileUtil.readClassPathFile(caseFilePath);
            XastMultiLineCommentParser parser = new XastMultiLineCommentParser(javaSourceCode);
            XastMultiLineCommentParseResult result = parser.parse();
            Assertions.assertFalse(result.isHasMultilineComment());
        }

        // 已经有注释的内容
        {
            String caseFilePath =
                    "testcase/parser/multiLineCommentParser/AccuracyTrackTaintObject_ParamSinkSameValue_002_F.java";
            String javaSourceCode = com.iast.astbenchmark.common.utils.FileUtil.readClassPathFile(caseFilePath);
            XastMultiLineCommentParser parser = new XastMultiLineCommentParser(javaSourceCode);
            XastMultiLineCommentParseResult result = parser.parse();
            Assertions.assertTrue(result.isHasMultilineComment());
        }

        // 有注释，但是注释是空的
        {
            String caseFilePath =
                    "testcase/parser/multiLineCommentParser/AccuracyTrackTaintObject_ParamSinkSameValue_003_F.java";
            String javaSourceCode = com.iast.astbenchmark.common.utils.FileUtil.readClassPathFile(caseFilePath);
            XastMultiLineCommentParser parser = new XastMultiLineCommentParser(javaSourceCode);
            XastMultiLineCommentParseResult result = parser.parse();
            Assertions.assertTrue(result.isHasMultilineComment());
        }

        // 注释有一些畸形
        {
            String caseFilePath =
                    "testcase/parser/multiLineCommentParser/AccuracyTrackTaintObject_ParamSinkSameValue_004_F.java";
            String javaSourceCode = com.iast.astbenchmark.common.utils.FileUtil.readClassPathFile(caseFilePath);
            XastMultiLineCommentParser parser = new XastMultiLineCommentParser(javaSourceCode);
            XastMultiLineCommentParseResult result = parser.parse();
            Assertions.assertTrue(result.isHasMultilineComment());
        }

    }

}