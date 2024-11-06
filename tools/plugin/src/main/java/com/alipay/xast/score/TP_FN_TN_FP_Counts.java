/**
 * OWASP Benchmark Project
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https://owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @author Dave Wichers
 * @created 2015
 */
package com.alipay.xast.score;

/**
 * This class simply tracks the counts for the true positives, false negatives, true negatives, and
 * false positives for a particular tool against the test suite.
 */
public class TP_FN_TN_FP_Counts {

    public int tp = 0;
    public int fn = 0;
    public int tn = 0;
    public int fp = 0;

    public TP_FN_TN_FP_Counts() {}

    public TP_FN_TN_FP_Counts(int tp, int fn, int tn, int fp) {
        this.tp = tp;
        this.fn = fn;
        this.tn = tn;
        this.fp = fp;
    }
}
