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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;

/*
 * This class is used by the crawlers to test the target Benchmark style web application. It tests Servlet style
 * web applications that use traditional GET parameters in URLs, POST body parameters, header name/values, cookies,
 * etc. Nothing fancy, specific to particular frameworks, like parameters embedded in the URL path, etc.
 */

@XmlDiscriminatorValue("SERVLET")
public class ServletTestCaseRequest extends AbstractTestCaseRequest {

    public ServletTestCaseRequest() {}

    @SuppressWarnings("deprecation")
    @Override
    void buildQueryString() {
        setQuery("");
        boolean first = true;
        for (RequestVariable field : getGetParams()) {
            if (first) {
                setQuery("?");
                first = false;
            } else {
                setQuery(getQuery() + "&");
            }
            String name = field.getName();
            String value = field.getValue();
            // System.out.println(query);
            setQuery(getQuery() + (name + "=" + URLEncoder.encode(value)));
        }
    }

    @Override
    HttpUriRequestBase createRequestInstance(String URL) {
        // If there are query parameters, this must be a GET, otherwise a POST.
        if (getQuery().length() == 0) {
            return new HttpPost(URL);
        } else {
            return new HttpGet(URL);
        }
    }

    @Override
    void buildHeaders(HttpUriRequestBase request) {
        // AJAX does: text/plain;charset=UTF-8, while HTML Form: application/x-www-form-urlencoded
        // request.addHeader("Content-Type", ";charset=UTF-8"); --This BREAKS BenchmarkCrawling
        request.addHeader(
                "Content-Type", "application/x-www-form-urlencoded"); // Works for both though

        for (RequestVariable header : getHeaders()) {
            String name = header.getName();
            String value = header.getValue();
            // System.out.println("Header:" + name + "=" + value);
            request.addHeader(name, value);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    void buildCookies(HttpUriRequestBase request) {
        for (RequestVariable cookie : getCookies()) {
            String name = cookie.getName();
            String value = cookie.getValue();
            // Note: URL encoding of a space becomes a +, which is OK for Java, but
            // not other languages. So after URLEncoding, replace all + with %20, which is the
            // standard URL encoding for a space char.
            request.addHeader("Cookie", name + "=" + URLEncoder.encode(value).replace("+", "%20"));
        }
    }

    @Override
    void buildBodyParameters(HttpUriRequestBase request) {
        List<NameValuePair> fields = new ArrayList<>();
        for (RequestVariable formParam : getFormParams()) {
            fields.add(formParam.getNameValuePair());
        }

        // Add the body parameters to the request if there were any
        if (fields.size() > 0) {
            request.setEntity(new UrlEncodedFormEntity(fields));
        }
    }
}
