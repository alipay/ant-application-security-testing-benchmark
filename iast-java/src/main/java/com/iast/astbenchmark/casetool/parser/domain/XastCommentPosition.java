package com.iast.astbenchmark.casetool.parser.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 用于标记注释在源代码文件中的位置，一个开始位置和结束位置标记一个注释块，处理源代码文件的时候最小单位是这个注释块
 *
 * @author CC11001100
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class XastCommentPosition {

    /**
     * xAST注释开始位置的位置
     */
    private int beginOffset;

    /**
     * xAST注释结束的位置
     */
    private int endOffset;

}
