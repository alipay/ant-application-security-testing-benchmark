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

import com.alipay.xast.Category;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorNode;

@XmlSeeAlso({ServletTestCase.class, JerseyTestCase.class, SpringTestCase.class})
@XmlDiscriminatorNode("@tcType")
public abstract class TestCase {

    private String url;

    private Category category;

    private String dataflowFile;

    private String name;

    private String notAutoverifiableReason;

    private String sinkFile;

    private String sourceFile;

    private String sourceUIType;

    private String templateFile;

    private String UITemplateFile;

    private boolean isVulnerable;

    private List<RequestVariable> formParameters;

    private List<RequestVariable> getParameters;

    private List<RequestVariable> cookies;

    private List<RequestVariable> headers;

    @XmlAttribute(name = "URL", required = true)
    @NotNull
    public String getUrl() {
        return url;
    }

    @XmlAttribute(name = "tcCategory", required = true)
    @XmlJavaTypeAdapter(CategoryAdapter.class)
    @NotNull
    public Category getCategory() {
        return category;
    }

    @XmlAttribute(name = "tcDataflowFile", required = true)
    @NotNull
    public String getDataflowFile() {
        return dataflowFile;
    }

    @XmlAttribute(name = "tcName", required = true)
    @NotNull
    public String getName() {
        return name;
    }

    @XmlAttribute(name = "tcNotAutoverifiable")
    public String getNotAutoverifiableReason() {
        return notAutoverifiableReason;
    }

    @XmlAttribute(name = "tcSinkFile", required = true)
    @NotNull
    public String getSinkFile() {
        return sinkFile;
    }

    @XmlAttribute(name = "tcSourceFile", required = true)
    @NotNull
    public String getSourceFile() {
        return sourceFile;
    }

    @XmlAttribute(name = "tcSourceUIType", required = true)
    @NotNull
    public String getSourceUIType() {
        return sourceUIType;
    }

    @XmlAttribute(name = "tcTemplateFile", required = true)
    @NotNull
    public String getTemplateFile() {
        return templateFile;
    }

    @XmlAttribute(name = "tcUITemplateFile", required = true)
    @NotNull
    public String getUITemplateFile() {
        return UITemplateFile;
    }

    @XmlAttribute(name = "tcVulnerable", required = true)
    public boolean isVulnerable() {
        return isVulnerable;
    }

    @XmlElement(name = "formparam")
    @NotNull
    public List<RequestVariable> getFormParameters() {
        return formParameters;
    }

    @XmlElement(name = "getparam")
    @NotNull
    public List<RequestVariable> getGetParameters() {
        return getParameters;
    }

    @XmlElement(name = "cookie")
    @NotNull
    public List<RequestVariable> getCookies() {
        return cookies;
    }

    @XmlElement(name = "header")
    @NotNull
    public List<RequestVariable> getHeaders() {
        return headers;
    }

    public void setFormParameters(List<RequestVariable> formParameters) {
        this.formParameters = formParameters;
    }

    public void setGetParameters(List<RequestVariable> getParameters) {
        this.getParameters = getParameters;
    }

    public void setCookies(List<RequestVariable> cookies) {
        this.cookies = cookies;
    }

    public void setHeaders(List<RequestVariable> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "TestCase [url="
                + url
                + ", category="
                + category
                + ", dataflowFile="
                + dataflowFile
                + ", name="
                + name
                + ", notAutoverifiableReason="
                + notAutoverifiableReason
                + ", sinkFile="
                + sinkFile
                + ", sourceFile="
                + sourceFile
                + ", sourceUIType="
                + sourceUIType
                + ", templateFile="
                + templateFile
                + ", UITemplateFile="
                + UITemplateFile
                + ", isVulnerable="
                + isVulnerable
                + ", formParameters="
                + formParameters
                + ", getParameters="
                + getParameters
                + ", cookies="
                + cookies
                + ", headers="
                + headers
                + "]";
    }
}
