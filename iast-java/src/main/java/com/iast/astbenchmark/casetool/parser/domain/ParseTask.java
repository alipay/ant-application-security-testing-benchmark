package com.iast.astbenchmark.casetool.parser.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 表示一个case源代码文件解析任务
 *
 * @author CC11001100
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParseTask {

    /**
     * case的java源代码文件的绝对路径
     */
    private String javaSourceFilePath;

    /**
     * case的java源代码文件中存放的类的名字，一个case文件中只存放
     */
    private String javaFullClassName;

}
