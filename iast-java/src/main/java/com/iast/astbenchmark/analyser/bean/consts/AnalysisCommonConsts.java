package com.iast.astbenchmark.analyser.bean.consts;

/**
 * 分析扫描出的结果的
 */
public class AnalysisCommonConsts {

    /**
     * 用于检出Tag的分隔符
     * 用于 AstTaintCase00*.aTaintcase****() 的分割
     * 这些信息通常存在sinkStack(iast) CodeLocation(seeker)中，需要这样的分隔符来获取到
     */
    public static final String TAG_SPLIT = "AstTaintCase00";

}
