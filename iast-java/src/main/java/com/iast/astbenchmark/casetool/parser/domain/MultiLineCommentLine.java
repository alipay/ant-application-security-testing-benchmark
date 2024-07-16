package com.iast.astbenchmark.casetool.parser.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 多行注释的时候，解析出来的每行注释，结构化的表示
 *
 * @author CC11001100
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultiLineCommentLine {

    /**
     * 这一行配置的名字，比如 Level
     */
    private String name;

    /**
     * 这一行配置的值，比如 X
     */
    private String value;

    /**
     * 这一行注释的位置，当编辑它的内容的时候会根据注释的位置来编辑
     */
    private XastCommentPosition position;

}
