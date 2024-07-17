package com.iast.astbenchmark.casetool.parser;

import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.casetool.parser.domain.CaseJavaFileParseResult;
import com.iast.astbenchmark.casetool.parser.executor.PipelineExecutor;
import com.iast.astbenchmark.common.XastIastException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于从 IastTestCaseDescriptor 上解析元信息
 *
 * @author CC11001100
 */
@Getter
public class IastTestCaseDescriptorParser {

    private final IastTestCaseDescriptor descriptor;

    private final CaseJavaFileParseResult result;

    private final PipelineExecutor<IastTestCaseDescriptor> executor;

    public IastTestCaseDescriptorParser(IastTestCaseDescriptor descriptor, CaseJavaFileParseResult result) {
        this.descriptor = descriptor;
        this.result = result;

        List<IastTestCaseDescriptor> descriptorList = this.initIastTestCaseDescriptorList(this.descriptor);
        this.executor = new PipelineExecutor<>(descriptorList);
    }

    public void parse() throws XastIastException {

        // 漏洞编号
        this.result.setCaseNo(this.executor.execute(IastTestCaseDescriptor::caseNo));

        // 是否期望有漏洞
        this.result.setHasVul(this.executor.execute(IastTestCaseDescriptor::hasVul));

        // 对此case的一些描述信息
        this.result.setDescription(this.executor.execute(IastTestCaseDescriptor::description));

        // 此case所属的分类
        this.result.setCategory(this.executor.execute(IastTestCaseDescriptor::category));

        // 此case是否期望有漏洞
        this.result.setHasVul(this.executor.execute(IastTestCaseDescriptor::hasVul));

    }

    /**
     * 初始化case描述符上下文
     *
     * @param descriptor
     * @return
     */
    private List<IastTestCaseDescriptor> initIastTestCaseDescriptorList(IastTestCaseDescriptor descriptor) {
        List<IastTestCaseDescriptor> descriptorList = new ArrayList<>(2);
        descriptorList.add(descriptor);
        if (descriptor.followIastTestCaseDescriptor() != null) {
            descriptorList.add(descriptor.followIastTestCaseDescriptor());
        }
        return descriptorList;
    }

}
