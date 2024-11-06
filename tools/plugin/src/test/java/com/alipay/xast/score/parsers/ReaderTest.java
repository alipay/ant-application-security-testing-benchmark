package com.alipay.xast.score.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alipay.xast.score.BenchmarkScore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ReaderTest {

    @ParameterizedTest(name = "{index} {0}")
    @ValueSource(
            strings = {
                "BenchmarkTest00042",
                "/BenchmarkTest00042",
                "c:\\somepath\\BenchmarkTest00042",
                "c:/somepath/BenchmarkTest00042",
                "/somepath/BenchmarkTest00042",
                // seeker
                "/benchmark/cmdi-00/BenchmarkTest00042",
                // Wapiti
                "/benchmark/sqli-01/BenchmarkTest00042",
                "http://somewhere/BenchmarkTest00042.html",
                "https://somewhere:8443/BenchmarkTest00042.html",
                "https://example.com:18080/test/BenchmarkTest00042.java#example",
                "https://example.com:18080/test/BenchmarkTest00042.java#examp32le",
                "BenchmarkTest0042.java",
                // HCLAppScanSoruce
                "BenchmarkTest00042:99",
                // NJSScan
                "BenchmarkTestv2.00042.java",
                // Julia
                "org.owasp.benchmark.testcode.BenchmarkTest00042.java",
                // Fortify
                "BenchmarkTest00042$InnerClass",
                // Contrast
                "/benchmark/cmdi-00/BenchmarkTest00042",
                "/benchmark/rest/xxe-00/BenchmarkTest00042/send",
                // Hdiv
                "This is a test line for the /BenchmarkTest00042 test.",
                "This is a test line for the /BenchmarkTest00042 test another testing. .",
                "05:41:58,220 ANL [FINE] benchmark SourceCodeVulnerability [origin=REQUEST, type=PATH_TRAVERSAL, url=/benchmark/pathtraver-01/BenchmarkTest00042, httpParameterName=BenchmarkTest00042, httpOriginalValue=FileName, taintedValue=SomeValue, className=org.owasp.benchmark.testcode.BenchmarkTest00001, lineNumber=1, score=1.0, hash=0000000000]",
                "https://localhost:8443/benchmark/xss-05/BenchmarkTest00042?BenchmarkTest00042=%3Cscript%3Ealert%281%29%3B%3C%2Fscript%3E&password=ZAP&username=ZAP"
            })
    public void readsTestNumberFromPath(String path) {
        BenchmarkScore.TESTCASENAME = "BenchmarkTest";
        assertEquals(42, Reader.testNumber(path));
    }

    @Test
    public void returnsInvalidNumberForNonMatchingPrefix() {
        BenchmarkScore.TESTCASENAME = "SomethingElse";
        assertEquals(-1, Reader.testNumber("/somepath/BenchmarkTest00042"));
    }

    @Test
    public void returnsInvalidNumberForPathWithoutTestfile() {
        BenchmarkScore.TESTCASENAME = "BenchmarkTest";
        assertEquals(-1, Reader.testNumber("/somepath/someotherfile"));
    }

    @ParameterizedTest(name = "{index} {0}")
    @CsvSource(
            value = {
                "BenchmarkTest00042|BenchmarkTest00042",
                "/BenchmarkTest00042|BenchmarkTest00042",
                "c:\\somepath\\BenchmarkTest00042|BenchmarkTest00042",
                "c:/somepath/BenchmarkTest00042|BenchmarkTest00042",
                "/somepath/BenchmarkTest00042|BenchmarkTest00042",
                "http://somewhere/BenchmarkTest00042.html|BenchmarkTest00042.html",
                "http://somewhere/BenchmarkTest00042.html?foo=bar|BenchmarkTest00042.html",
                "https://somewhere:8443/BenchmarkTest00042.html|BenchmarkTest00042.html",
                "/something/else|else",
                "/something/else.html|else.html"
            },
            delimiter = '|')
    public void extractsFilenameFromPath(String input, String expected) {
        assertEquals(expected, Reader.extractFilename(input));
    }
}
