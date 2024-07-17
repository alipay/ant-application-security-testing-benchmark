package com.iast.astbenchmark.casetool.parser.comment;

import com.iast.astbenchmark.casetool.parser.XastCaseParserException;
import com.iast.astbenchmark.casetool.parser.domain.XastCommentPosition;
import com.iast.astbenchmark.casetool.parser.domain.XastMultiLineCommentParseResult;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * 用于解析case文件中的多行注释
 *
 * @author CC11001100
 */
public class XastMultiLineCommentParser extends BaseXastCommentParser {

    /**
     * 多行注释开始的标志
     */
    public static final String MULTI_LINE_BEGIN_FLAG = "/**";

    /**
     * 多行注释结束的标志
     */
    public static final String MULTI_LINE_END_FLAG = "*/";

    /**
     * 在解析的case源代码的内容
     */
    private final String caseJavaSourceCode;

    /**
     * 当前读取到的内容偏移
     */
    private int readIndex;

    /**
     * 类声明的位置
     */
    @Getter
    private final int classIndex;

    /**
     * 解析给定的case源文件
     *
     * @param caseJavaSourceCode
     */
    public XastMultiLineCommentParser(String caseJavaSourceCode) throws XastCaseParserException {

        if (StringUtils.isBlank(caseJavaSourceCode)) {
            throw new XastCaseParserException("The source code for the case cannot be empty.");
        }

        this.caseJavaSourceCode = caseJavaSourceCode;
        this.readIndex = 0;

        // 只解析类上的注释，所以这里设置一个下标限定
        this.classIndex = this.caseJavaSourceCode.indexOf("public class");
    }

    /**
     * 判断多行注释的解析是否结束
     *
     * @return
     */
    private boolean isEnd() {
        // 读取的时候不超过类声明的位置，超过的话就认为解析结束
        return this.readIndex >= this.classIndex;
    }

    /**
     * 解析源代码中的多行注释
     *
     * @return
     */
    public XastMultiLineCommentParseResult parse() {

        XastMultiLineCommentParseResult result = new XastMultiLineCommentParseResult();

        // 找到第一个多行注释
        if (!this.findNextMultiLineBeginLine()) {
            return result;
        }

        int positionBegin = this.readIndex - 1;
        StringBuilder comment = new StringBuilder();
        while (!this.isEnd()) {
            String lineString = this.readNextLine();

            // 读取到结束也没有找到多行注释的结尾，哦豁这个多行注释没有闭合，那就算了就当没有吧
            if (lineString == null) {
                return result;
            }
            comment.append(lineString);

            // 如果读取到了多行注释的结尾，则结束本多行注释块的解析，说明读取完整了
            if (MULTI_LINE_END_FLAG.equals(lineString.trim())) {
                result.setHasMultilineComment(true);
                result.setComment(comment.toString());
                result.setPosition(new XastCommentPosition(positionBegin, this.readIndex + 1));
                return result;
            }
        }

        return result;
    }

    /**
     * 读取当前行剩下的内容，直到遇到换行符
     *
     * @return
     */
    private String readNextLine() {
        int lineEndIndex = this.caseJavaSourceCode.indexOf("\n", this.readIndex);
        if (lineEndIndex == -1) {
            return null;
        }
        String line = this.caseJavaSourceCode.substring(this.readIndex, lineEndIndex + 1);
        // 消费掉换行符
        this.readIndex = lineEndIndex + 1;
        return line;
    }

    /**
     * 读取源代码内容直到找到下一个多行注释开始的位置
     *
     * @return 是否找到了多行注释
     */
    private boolean findNextMultiLineBeginLine() {
        int multiLineCommentBeginIndex = this.caseJavaSourceCode.indexOf(MULTI_LINE_BEGIN_FLAG, this.readIndex);

        // 哦豁，没有找到多行注释
        if (multiLineCommentBeginIndex < 0) {
            return false;
        }

        // 移动读取指针到多行注释的开始位置
        this.readIndex = multiLineCommentBeginIndex;
        return !this.isEnd();
    }

}
