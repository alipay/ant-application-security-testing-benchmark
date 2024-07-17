package com.iast.astbenchmark.casetool.parser.executor;

import com.iast.astbenchmark.common.XastIastException;
import lombok.Getter;

import java.util.List;

/**
 * @author CC11001100
 */
@Getter
public class PipelineExecutor<Context> {

    private final List<Context> contextList;

    public PipelineExecutor(List<Context> contextList) {
        this.contextList = contextList;
    }

    /**
     * @param task
     * @return
     * @throws XastIastException
     */
    public <Result> Result execute(PipelineTask<Context, Result> task) throws XastIastException {
        for (Context context : this.contextList) {
            Result result = task.execute(context);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

}
