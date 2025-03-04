/**
 * OWASP Benchmark Project
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https:/owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @author David Anderson
 * @created 2021
 */
package com.alipay.xast.helpers;

import com.alipay.xast.tools.AbstractTestCaseRequest;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "benchmarkSuite")
public class TestSuite {
    private List<AbstractTestCaseRequest> testCases;

    private String name; // Name of the test suite, e.g., benchmark (Which is BenchmarkJava)

    private String version;

    @XmlElement(name = "benchmarkTest")
    public List<AbstractTestCaseRequest> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<AbstractTestCaseRequest> testCases) {
        this.testCases = testCases;
    }

    @XmlAttribute(name = "testsuite", required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "version", required = true)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Dump out some basic details from the Crawler file to the command line to verify it was read
     * in properly. Used for debugging.
     */
    public void dumpBasicDetails() {
        System.out.println("Test suite name and version: " + name + " v" + version);
        System.out.println("Total test cases: " + this.getTestCases().size());
    }

    @Override
    public String toString() {
        return "TestSuite [testCases=" + testCases + "]";
    }
}
