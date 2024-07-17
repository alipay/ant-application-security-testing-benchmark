package com.iast.astbenchmark.analyser;

/**
 *
 * TODO 同于统一管理测试用例的目录
 *
 * @author CC11001100
 */
public class Category {

    // -------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 分类卷级别之间使用的分隔符
     */
    public static final String CATEGORY_LEVEL_DELIMITER = " -> ";

    // -------------------------------------------------------------------------------------------------------------------------------------

    public static final String LEVEL1_IAST_JAVA_ENGINE = "IAST引擎能力评估体系(JAVA)";

    // -------------------------------------------------------------------------------------------------------------------------------------

    public static final String LEVEL2_准确度 = "准确度";

    public static final String LEVEL2_完整度 = "完整度";

    // -------------------------------------------------------------------------------------------------------------------------------------

    public static final String LEVEL3_基础跟踪能力 = "基础跟踪能力";
    public static final String LEVEL3_污点对象跟踪粒度 = "污点对象跟踪粒度";
    public static final String LEVEL3_异步跟踪能力 = "异步跟踪能力";

    // -------------------------------------------------------------------------------------------------------------------------------------

    public static final String LEVEL4_污点对象完整度 = "污点对象完整度";

    public static final String LEVEL4_变量级别 = "变量级别";

    public static final String LEVEL4_污点链路完整度 = "污点链路完整度";

    public static final String LEVEL4_字段_元素级别 = "字段/元素级别";

    public static final String LEVEL4_字符串级别 = "字符串级别";
    public static final String LEVEL4_多线程异步 = "多线程异步";

    // -------------------------------------------------------------------------------------------------------------------------------------

    public static final String LEVEL5_数组_数组对象全为污点 = "数组(数组对象全为污点)";
    public static final String LEVEL5_集合_集合对象全为污点 = "集合(集合对象全为污点)";
    public static final String LEVEL5_基本数据类型及其封装类型 = "基本数据类型及其封装类型";

    public static final String LEVEL5_污点无害化处理能力 = "污点无害化处理能力(sanitizer)";

    public static final String LEVEL5_字符串部分存在污点 = "字符串部分存在污点";

    public static final String LEVEL5_部分字段对象为污点 = "部分字段对象为污点";

    public static final String LEVEL5_特殊链路跟踪能力 = "特殊链路跟踪能力";

    public static final String LEVEL5_污点来源识别能力 = "污点来源识别能力(source)";

    public static final String LEVEL5_污点传播跟踪能力 = "污点传播跟踪能力";

    // -------------------------------------------------------------------------------------------------------------------------------------

    public static final String LEVEL6_数组对象byte = "数组对象byte";

    public static final String LEVEL6_污点直接赋值为硬编码值 = "污点直接赋值为硬编码值";

    public static final String LEVEL6_超长链路追踪_100 = "超长链路追踪（100层）";

    public static final String LEVEL6_超长链路追踪_1000 = "超长链路追踪（1000层）";

    public static final String LEVEL6_污点来自HttpBody = "污点来自http body";

    public static final String LEVEL6_传播场景 = "传播场景";

    // -------------------------------------------------------------------------------------------------------------------------------------

    public static final String LEVEL7_JSON_REQUEST_BODY = "json/RequestBody";

    public static final String LEVEL7_StringBuilder = "StringBuilder操作";
    public static final String LEVEL7_String = "String操作";

    // -------------------------------------------------------------------------------------------------------------------------------------

    public static final String 污点对象跟踪粒度 = "污点对象跟踪粒度";

    // -------------------------------------------------------------------------------------------------------------------------------------

}
