package com.iast.astbenchmark.casetool.parser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单行注释的解析结果
 *
 * @author CC11001100
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XastSingleLineCommentParseResult {

    /**
     * 这个case文件中是否有单行注释
     */
    private boolean hasSingleComment;

    /**
     * xast单行注释块的位置，生成注释的时候会把这个单行注释块直接全部替换掉
     */
    private XastCommentPosition position;

    /**
     * xast单行注释块的内容
     */
    private String comment;

}