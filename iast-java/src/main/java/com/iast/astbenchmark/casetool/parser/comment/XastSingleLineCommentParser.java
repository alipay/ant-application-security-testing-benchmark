package com.iast.astbenchmark.casetool.parser.comment;

import com.iast.astbenchmark.casetool.parser.XastCaseParserException;
import com.iast.astbenchmark.casetool.parser.domain.XastCommentPosition;
import com.iast.astbenchmark.casetool.parser.domain.XastSingleLineCommentParseResult;

/**
 * 解析case源代码中与xast有关的单行注释
 *
 * @author CC11001100
 */
public class XastSingleLineCommentParser extends BaseXastCommentParser {

    /**
     * 单行注释首行以这个开头
     */
    public static final String XAST_COMMENT_BEGIN_FLAG = "// assession information start";

    /**
     * 单行注释尾行以这个结尾
     */
    public static final String XAST_COMMENT_END_FLAG = "// assession information end";

    /**
     * 从源文件中解析出来xAST注释的位置
     *
     * @param javaCaseSourceCode
     * @return
     * @throws XastCaseParserException
     */
    public XastSingleLineCommentParseResult parseXastCommentPosition(String javaCaseSourceCode) throws XastCaseParserException {
        XastSingleLineCommentParseResult result = new XastSingleLineCommentParseResult();
        int beginIndex = javaCaseSourceCode.indexOf(XAST_COMMENT_BEGIN_FLAG);
        int endIndex = javaCaseSourceCode.indexOf(XAST_COMMENT_END_FLAG);
        if (beginIndex < 0 && endIndex < 0) {
            // 没有找到有效的xAST注释
            // return this.findXastCommentInsertPosition(javaSourceCode);
            // return this.findXastCommentInsertPosition(javaSourceCode);
            return result;
        }
        if (beginIndex < 0 || endIndex < 0) {
            // TODO 2024-06-13 15:43:35 异常信息
            throw new XastCaseParserException("");
        }
        // 把前后的换行符也包括进来
        int positionBegin = beginIndex -1 ;
        int positionEnd = endIndex + XAST_COMMENT_END_FLAG.length() + 1;
        XastCommentPosition position = new XastCommentPosition(positionBegin, positionEnd);
        result.setPosition(position);
        result.setHasSingleComment(true);
        result.setComment(javaCaseSourceCode.substring(positionBegin, positionEnd));
        return result;
    }

}
