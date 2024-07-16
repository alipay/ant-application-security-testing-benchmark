package com.iast.astbenchmark.casetool.parser.comment;

import com.iast.astbenchmark.casetool.parser.XastCaseParserException;
import com.iast.astbenchmark.casetool.parser.domain.XastSingleLineCommentParseResult;
import com.iast.astbenchmark.common.utils.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author CC11001100
 */
class XastSingleLineCommentParserTest {

    @Test
    void parseXastCommentPosition() throws XastCaseParserException {

        // case: 没有xast单行注释
        {
            String caseFileClassPath = "testcase/parser/singleLineCommentParser/AccuracyTrackTaintObject_ParamSinkSameValue_001_F.java";
            XastSingleLineCommentParser parser = new XastSingleLineCommentParser();
            XastSingleLineCommentParseResult result = parser.parseXastCommentPosition(FileUtil.readClassPathFile(caseFileClassPath));
            Assertions.assertFalse(result.isHasSingleComment());
        }

        // case: 有xast单行注释，并且单行注释是符合格式的
        {
            String caseFileClassPath = "testcase/parser/singleLineCommentParser/AccuracyTrackTaintObject_ParamSinkSameValue_002_F.java";
            XastSingleLineCommentParser parser = new XastSingleLineCommentParser();
            XastSingleLineCommentParseResult result = parser.parseXastCommentPosition(FileUtil.readClassPathFile(caseFileClassPath));
            Assertions.assertTrue(result.isHasSingleComment());
        }

        // case: 有xast单行注释，但是单行注释的位置其实不太对，也没关系，反正你爱放在哪儿是哪儿
        {
            String caseFileClassPath = "testcase/parser/singleLineCommentParser/AccuracyTrackTaintObject_ParamSinkSameValue_003_F.java";
            XastSingleLineCommentParser parser = new XastSingleLineCommentParser();
            XastSingleLineCommentParseResult result = parser.parseXastCommentPosition(FileUtil.readClassPathFile(caseFileClassPath));
            Assertions.assertTrue(result.isHasSingleComment());
        }


    }

}