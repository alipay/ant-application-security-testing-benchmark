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
 * PURPOSE. See the GNU General Public License for more details
 *
 * @author Juan Gama
 * @created 2017
 */
package com.alipay.xast.tools;

import com.alipay.xast.helpers.RequestVariable;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;

@XmlDiscriminatorValue("SPRINGWS")
public class SpringTestCaseRequest extends AbstractTestCaseRequest {

    public SpringTestCaseRequest() {}

    @Override
    void buildQueryString() {
        setQuery("");
    }

    @Override
    HttpUriRequestBase createRequestInstance(String URL) {
        // Apparently all Spring Requests are POSTS. Never any query string params per buildQuery()
        // above.
        HttpPost httpPost = new HttpPost(URL);
        return httpPost;
    }

    @Override
    void buildHeaders(HttpUriRequestBase request) {
        request.addHeader("Content-type", "application/json"); // Should this add ;charset=utf-8?
        // No: "Designating the encoding is somewhat redundant for JSON, since the default encoding
        // for JSON is UTF-8."
        for (RequestVariable header : getHeaders()) {
            String name = header.getName();
            String value = header.getValue();
            System.out.println("Header:" + name + "=" + value);
            request.addHeader(name, value);
        }
    }

    @Override
    void buildCookies(HttpUriRequestBase request) {
        for (RequestVariable cookie : getCookies()) {
            String name = cookie.getName();
            String value = cookie.getValue();
            // System.out.println("Cookie:" + name + "=" + value);
            request.addHeader("Cookie", name + "=" + value);
        }
    }

    @Override
    void buildBodyParameters(HttpUriRequestBase request) {
        boolean first = true;
        String params = "{";
        for (RequestVariable field : getFormParams()) {
            String name = field.getName();
            String value = field.getValue();
            // System.out.println(name+"="+value);
            if (first) {
                first = false;
            } else {
                params = params + ",";
            }
            params = params + String.format("\"%s\":\"%s\"", name, value.replace("\"", "\\\""));
        }
        params += "}";
        StringEntity paramsEnt = new StringEntity(params);
        request.setEntity(paramsEnt);
    }
}
