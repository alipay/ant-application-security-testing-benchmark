package com.iast.astbenchmark.analyser.cache;

/**
 *
 * 使用这个接口来描述测试case的元信息
 *
 * @author CC11001100
 */
public interface IastTestCaseDescriptor {

    /**
     * Case编码
     *
     * @return
     */
    default String caseNo() {
        return null;
    }

    /**
     * 用于简介介绍测试case的作用，不强制，但是推荐最好能有描述信息
     *
     * @return
     */
    default String description() {
        return null;
    }

    /// **
    // * case 全名包括路径
    // *
    // * @return
    // */
    // @Deprecated
    // String caseFullName();

    /**
     * 测试用例所属的分类，一类一类的分级
     *
     * @return
     */
    default String[] category() {
        return null;
    }

    /**
     * 这个方法期望检出漏洞的结果,true为期待检出,false为不期待检出，不覆写方法的话默认case是要检出漏洞的
     *
     * @return
     */
    default Boolean hasVul() {
        return true;
    }

    /**
     * 这个方法的标识，可以用于日志等检出结果中检索
     *
     * @return
     */
    default String thisMethodTag() {
        return null;
    }

    /**
     * 自己不指定属性，只是跟随给定的属性
     *
     * @return
     */
    default IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return null;
    }

}
