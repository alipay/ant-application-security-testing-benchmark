package com.alipay.xast.score.builder;

import com.alipay.xast.score.CategoryResults;
import com.alipay.xast.score.ToolResults;
import java.util.HashSet;
import java.util.Set;

public class ToolResultsBuilder {

    private Set<CategoryResults> categoryResults = new HashSet<>();
    private double truePositiveRate = 0;
    private double falsePositiveRate = 0;
    private double precision = 0;

    private ToolResultsBuilder() {}

    public static ToolResultsBuilder builder() {
        return new ToolResultsBuilder();
    }

    public ToolResults build() {
        ToolResults results = new ToolResults();

        results.setTruePositiveRate(truePositiveRate);
        results.setFalsePositiveRate(falsePositiveRate);
        results.setPrecision(precision);

        categoryResults.forEach(
                cr ->
                        results.add(
                                cr.category,
                                cr.precision,
                                cr.truePositiveRate,
                                cr.falsePositiveRate,
                                cr.totalTestCases));

        return results;
    }

    public ToolResultsBuilder setCategoryResults(Set<CategoryResults> categoryResultsMap) {
        this.categoryResults = categoryResultsMap;

        return this;
    }

    public ToolResultsBuilder addCategoryResult(CategoryResults result) {
        this.categoryResults.add(result);

        return this;
    }

    public ToolResultsBuilder setTruePositiveRate(double truePositiveRate) {
        this.truePositiveRate = truePositiveRate;

        return this;
    }

    public ToolResultsBuilder setFalsePositiveRate(double falsePositiveRate) {
        this.falsePositiveRate = falsePositiveRate;

        return this;
    }

    public ToolResultsBuilder setPrecision(double precision) {
        this.precision = precision;

        return this;
    }
}
