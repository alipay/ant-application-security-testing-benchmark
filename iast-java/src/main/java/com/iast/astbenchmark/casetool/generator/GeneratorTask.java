package com.iast.astbenchmark.casetool.generator;

import java.util.List;

import com.iast.astbenchmark.casetool.parser.domain.CaseJavaFileParseResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CC11001100
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneratorTask {

    /**
     * 要生成注释的case文件
     */
    private CaseJavaFileParseResult caseJavaFileParseResult;

    /**
     * 如果此case有依赖其他文件的话
     */
    private List<CaseJavaFileParseResult> groupCaseList;

}
