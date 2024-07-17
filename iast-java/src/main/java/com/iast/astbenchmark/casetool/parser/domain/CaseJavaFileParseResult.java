package com.iast.astbenchmark.casetool.parser.domain;

import lombok.Data;

/**
 * case的java源代码文件的解析结果
 *
 * @author CC11001100
 */
@Data
public class CaseJavaFileParseResult {

    /**
     * case对应的java源代码文件的绝对路径
     */
    private String javaSourceFilePath;

    /**
     * 读取到的java源代码内容，java代码
     */
    private String javaSourceCode;

    /**
     * 此case对应的类，后面会从这个Class上获取一些元信息
     */
    private Class caseClass;

    /**
     * 此case对应的实例，是从Class上通过无参构造函数创建的实例
     */
    private Object caseObject;

    /**
     * 此case的描述信息
     */
    private String description;

    /**
     * 此case的编号
     */
    private String caseNo;

    /**
     * 所属的分类，是一个字符串数组，从高到低存放着每一级别的分类的名称
     */
    private String[] category;

    /**
     * 此case是否包含漏洞
     */
    private Boolean hasVul;

    /**
     * case绑定到的url映射
     */
    private String url;

    /**
     * xAST的注释的位置信息
     */
    private XastSingleLineCommentParseResult xastSingleLineCommentParseResult;

    /**
     * case文件中的多行注释
     */
    private XastMultiLineCommentParseResult xastMultiLineCommentParseResult;

    /**
     * 类定义的下标位置，注释要插入到这个位置
     */
    private Integer publicClassDefineIndex;

}
