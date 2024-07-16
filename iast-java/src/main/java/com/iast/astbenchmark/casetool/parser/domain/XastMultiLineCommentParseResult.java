package com.iast.astbenchmark.casetool.parser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CC11001100
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XastMultiLineCommentParseResult {

    /**
     * 是否有多行注释
     */
    private boolean hasMultilineComment;

    /**
     * 多行注释的位置
     */
    private XastCommentPosition position;



    /**
     * 多行注释块的内容
     */
    private String comment;

}
