package com.iast.astbenchmark.casetool.parser;

import cn.hutool.core.io.FileUtil;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.casetool.parser.comment.XastMultiLineCommentParser;
import com.iast.astbenchmark.casetool.parser.comment.XastSingleLineCommentParser;
import com.iast.astbenchmark.casetool.parser.domain.CaseJavaFileParseResult;
import com.iast.astbenchmark.casetool.parser.domain.ParseTask;
import com.iast.astbenchmark.casetool.parser.domain.XastMultiLineCommentParseResult;
import com.iast.astbenchmark.casetool.parser.domain.XastSingleLineCommentParseResult;
import com.iast.astbenchmark.common.XastIastException;

/**
 * 用于解析case的java源代码文件，将其结构化，提取需要的信息
 *
 * @author CC11001100
 */
public class CaseJavaSourceCodeFileParser {

    /**
     * 解析给定的case源代码文件
     *
     * @param task
     * @return
     * @throws XastCaseParserException
     */
    public CaseJavaFileParseResult parse(ParseTask task)
            throws XastIastException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // 确保case文件存在
        if (!FileUtil.exist(task.getJavaSourceFilePath())) {
            throw new XastCaseParserException(String.format("Task case file %s not exists", task.getJavaSourceFilePath()));
        }

        String javaSourceCode = FileUtil.readUtf8String(task.getJavaSourceFilePath());

        // 传递的任务上下文参数保留着
        CaseJavaFileParseResult result = new CaseJavaFileParseResult();
        result.setJavaSourceCode(javaSourceCode);
        result.setJavaSourceFilePath(task.getJavaSourceFilePath());

        // 解析xast相关的单行注释
        XastSingleLineCommentParseResult singleLineCommentParseResult = new XastSingleLineCommentParser().parseXastCommentPosition(javaSourceCode);
        result.setXastSingleLineCommentParseResult(singleLineCommentParseResult);

        // 解析xast相关的多行注释
        XastMultiLineCommentParser xastMultiLineCommentParser = new XastMultiLineCommentParser(javaSourceCode);
        XastMultiLineCommentParseResult multiLineCommentParseResult = xastMultiLineCommentParser.parse();
        result.setXastMultiLineCommentParseResult(multiLineCommentParseResult);
        result.setPublicClassDefineIndex(xastMultiLineCommentParser.getClassIndex());

        // 为case类创建实例，并从类上提取元信息
        Class<?> caseClass = Class.forName(task.getJavaFullClassName());
        if (caseClass.isInterface()) {
            return result;
        }
        Object caseObject = caseClass.newInstance();
        result.setCaseClass(caseClass);
        result.setCaseObject(caseObject);

        // 解析描述符上的元信息
        if (caseObject instanceof IastTestCaseDescriptor) {
            IastTestCaseDescriptor descriptor = (IastTestCaseDescriptor)caseObject;
            new IastTestCaseDescriptorParser(descriptor, result).parse();
        }

        // 解析case绑定到的url
        String bindUrl = new RequestMappingUrlParser().parse(caseClass);
        result.setUrl(bindUrl);

        return result;
    }

}
