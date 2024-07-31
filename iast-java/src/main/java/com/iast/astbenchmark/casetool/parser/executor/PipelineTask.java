package com.iast.astbenchmark.casetool.parser.executor;

import com.iast.astbenchmark.common.XastIastException;

/**
 *
 *
 * @author CC11001100
 */
@FunctionalInterface
public interface PipelineTask<Context, Result> {

    /**
     *
     *
     * @param context
     * @return
     * @throws XastIastException
     */
    Result execute(Context context) throws XastIastException;

}
