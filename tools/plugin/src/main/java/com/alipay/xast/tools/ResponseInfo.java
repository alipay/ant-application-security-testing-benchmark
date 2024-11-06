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
 * @created 2021
 */
package com.alipay.xast.tools;

import org.apache.hc.client5.http.classic.methods.HttpUriRequest;

class ResponseInfo {
    private String responseString;
    private int seconds;
    private int statusCode;
    private HttpUriRequest requestBase;

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public int getTimeInSeconds() {
        return seconds;
    }

    public void setTimeInSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpUriRequest getRequestBase() {
        return requestBase;
    }

    public void setRequestBase(HttpUriRequest request) {
        this.requestBase = request;
    }
}
