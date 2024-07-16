package com.iast.astbenchmark.casetool.generator;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.casetool.parser.domain.CaseJavaFileParseResult;
import com.iast.astbenchmark.casetool.parser.domain.XastCommentPosition;
import com.iast.astbenchmark.casetool.parser.domain.XastMultiLineCommentParseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于根据case的配置情况为其生成xast规则的注释
 *
 * @author CC11001100
 */
@Slf4j
public class XastCommentGenerator {

    /**
     * 根据传进来的任务生成xast注释
     *
     * @param task
     * @return
     */
    public String generate(GeneratorTask task) throws XastGeneratorException {
        CaseJavaFileParseResult result = task.getCaseJavaFileParseResult();
        String singleLineXastComment = this.buildSingleLineXastComment(result);
        String multiLineXastComment = this.buildMultiLineXastComment(result);
        return this.replaceXastComment(result, singleLineXastComment, multiLineXastComment);
    }

    /**
     * 使用新的xast注释把源代码覆盖掉
     *
     * @param result
     * @param xastSingleLineComment
     * @param xastMultiLineComment
     * @return
     * @throws XastGeneratorException
     */
    private String replaceXastComment(CaseJavaFileParseResult result, String xastSingleLineComment, String xastMultiLineComment)
            throws XastGeneratorException {

        String javaSourceCode = result.getJavaSourceCode();
        StringBuilder newJavaSourceCode = new StringBuilder();

        // 多行注释
        int nextBegin = -1;
        if (result.getXastMultiLineCommentParseResult() != null && result.getXastMultiLineCommentParseResult().isHasMultilineComment()) {
            // 多行注释存在，则直接把多行注释替换掉
            XastMultiLineCommentParseResult multiLineCommentParseResult = result.getXastMultiLineCommentParseResult();
            XastCommentPosition position = multiLineCommentParseResult.getPosition();
            newJavaSourceCode.append(javaSourceCode.substring(0, position.getBeginOffset()));
            newJavaSourceCode.append(xastMultiLineComment);
            nextBegin = position.getEndOffset();
        } else {
            // 多行注释不存在，则插入一个新的多行注释
            int delimiterIndex = result.getPublicClassDefineIndex();
            if (result.getXastSingleLineCommentParseResult() != null) {
                delimiterIndex = result.getXastSingleLineCommentParseResult().getPosition().getBeginOffset();
            }
            newJavaSourceCode.append(javaSourceCode.substring(0, delimiterIndex));
            nextBegin = delimiterIndex;
        }

        // 单行注释
        if (result.getXastSingleLineCommentParseResult() != null && result.getXastSingleLineCommentParseResult().isHasSingleComment()) {
            // 单行注释已经存在
            XastCommentPosition position = result.getXastSingleLineCommentParseResult().getPosition();
            if (position.getBeginOffset() > nextBegin) {
                newJavaSourceCode.append(javaSourceCode.substring(nextBegin - 1, position.getBeginOffset()));
            }
            newJavaSourceCode.append(xastSingleLineComment);
            nextBegin = position.getEndOffset();
        } else {
            // 单行注释不存在，则插入一个新的单行注释
            if (result.getPublicClassDefineIndex() > nextBegin) {
                newJavaSourceCode.append(javaSourceCode.substring(nextBegin - 1, result.getPublicClassDefineIndex()));
            }
            newJavaSourceCode.append(xastSingleLineComment);
            nextBegin = result.getPublicClassDefineIndex();
        }

        // 把余下的部分都追加进来
        newJavaSourceCode.append(javaSourceCode.substring(nextBegin, javaSourceCode.length()));

        // FileUtil.writeUtf8String(newJavaSourceCode.toString(), new File(result.getJavaSourceFilePath()));
        return newJavaSourceCode.toString();
    }

    /**
     * 根据解析到的case的元信息生成xast注释
     *
     * <pre>
     *
     * // assession information start
     * // real vulnerability = true
     * // assession project = 准确度->域敏感->对象部分属性为污点->Queue+Lambda
     * // compose = !PropertyIsTaintOrNot_Queue_Lambda_002_F.java && PropertyIsTaintOrNot_Queue_Lambda_001_T.java
     * // bind_url = accuracy/fieldSensitive/PropertyIsTaintOrNot_Queue_Lambda_001_T
     * // assession information end
     *
     * </pre>
     *
     * @param task
     * @return
     */
    private String buildSingleLineXastComment(CaseJavaFileParseResult result) {

        StringBuilder sb = new StringBuilder();

        sb.append("\n").append("// assession information start").append("\n");

        if (result.getHasVul() != null) {
            sb.append("// real vulnerability = ").append(result.getHasVul()).append("\n");
        }

        String category = String.join(Category.CATEGORY_LEVEL_DELIMITER, result.getCategory());
        sb.append("// assession project = ").append(category).append("\n");

        sb.append("// compose = ").append("\n");

        if (result.getUrl() != null) {
            sb.append("// bind_url = ").append(result.getUrl()).append("\n");
        }

        sb.append("// assession information end").append("\n");

        return sb.toString();
    }

    /**
     * 构建一个xast的多行注释
     *
     * @param result
     * @return
     */
    private String buildMultiLineXastComment(CaseJavaFileParseResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("/**").append("\n");
        sb.append("* Introduction ").append(StringUtils.defaultString(result.getDescription(), "X")).append("\n");
        sb.append("* Level X  ").append("\n");
        sb.append("* Date ").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).append("\n");
        sb.append("* @author CC11001100 ").append("\n");
        sb.append("*/").append("\n");
        return sb.toString();
    }

}
