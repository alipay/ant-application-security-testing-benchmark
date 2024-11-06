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

import com.alipay.xast.Category;
import com.alipay.xast.helpers.CategoryAdapter;
import com.alipay.xast.helpers.RequestVariable;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorNode;

@XmlSeeAlso({
    ServletTestCaseRequest.class,
    JerseyTestCaseRequest.class,
    SpringTestCaseRequest.class
})
@XmlDiscriminatorNode("@tcType")
public abstract class AbstractTestCaseRequest {

    /*
     * The 1st three are Java.
     */
    public enum TestCaseType {
        JERSEYWS,
        SERVLET,
        SPRINGWS,
        NODEEXPRESS
    }

    public static Comparator<AbstractTestCaseRequest> getNameComparator() {
        return new Comparator<AbstractTestCaseRequest>() {

            @Override
            public int compare(AbstractTestCaseRequest o1, AbstractTestCaseRequest o2) {
                if (!o1.name.equalsIgnoreCase(o2.name)) return o1.name.compareTo(o2.name);
                return 0;
            }
        };
    }

    private Category category;
    private List<RequestVariable> cookies = new ArrayList<RequestVariable>();
    private String dataflowFile;
    private List<RequestVariable> formParams = new ArrayList<RequestVariable>();
    private String fullURL;
    private List<RequestVariable> getParams = new ArrayList<RequestVariable>();
    private List<RequestVariable> headers = new ArrayList<RequestVariable>();
    private String notAutoverifiableReason;
    private boolean isUnverifiable;
    private boolean isVulnerability;
    private String attackSuccessString;

    // Occasionally, its useful to verify that a string is MISSING from the response to indicate an
    // attack was successful
    private boolean attackSuccessStringPresent = true; // The default

    private String name; // TestCase name
    private int number = -1; // TestCase number, auto extracted from the name when its set
    private String query;
    private String sinkFile;
    private String sourceFile;
    private String sourceUIType;
    private TestCaseType tcType;
    private String templateFile;
    private String uiTemplateFile;

    public AbstractTestCaseRequest() {}

    /** Defines what parameters in the body will be sent. */
    abstract void buildBodyParameters(HttpUriRequestBase request);

    /** Defines what cookies will be sent. */
    abstract void buildCookies(HttpUriRequestBase request);

    /** Defines what headers will be sent. */
    abstract void buildHeaders(HttpUriRequestBase request);

    /** Defines how to construct URL query string. */
    abstract void buildQueryString();

    /**
     * TODO: Make this class a POJO TestCase and pass it as an arg to another class TestCaseRequest
     * that can build an actual HttpUriRequest.
     *
     * @return
     */
    public HttpUriRequest buildRequest() {
        buildQueryString();
        HttpUriRequestBase request = createRequestInstance(fullURL + query);
        buildHeaders(request);
        buildCookies(request);
        buildBodyParameters(request);
        return request;
    }

    public HttpUriRequest buildAttackRequest() {
        setSafe(false);
        return buildRequest();
    }

    public HttpUriRequest buildSafeRequest() {
        setSafe(true);
        return buildRequest();
    }

    /**
     * Method to create a POST, GET, DELETE, HEAD, OPTIONS, TRACE request object.
     *
     * @return an instance of a subclass of HttpUriRequestBase
     */
    abstract HttpUriRequestBase createRequestInstance(String URL);

    @XmlAttribute(name = "tcAttackSuccess")
    public String getAttackSuccessString() {
        return this.attackSuccessString;
    }

    @XmlAttribute(name = "tcAttackSuccessPresent")
    public boolean getAttackSuccessStringPresent() {
        return this.attackSuccessStringPresent;
    }

    @XmlAttribute(name = "tcCategory", required = true)
    @XmlJavaTypeAdapter(CategoryAdapter.class)
    @NotNull
    public Category getCategory() {
        return this.category;
    }

    @XmlElement(name = "cookie")
    @NotNull
    public List<RequestVariable> getCookies() {
        return this.cookies;
    }

    @XmlAttribute(name = "tcDataflowFile", required = true)
    @NotNull
    public String getDataflowFile() {
        return this.dataflowFile;
    }

    @XmlElement(name = "formparam")
    @NotNull
    public List<RequestVariable> getFormParams() {
        return this.formParams;
    }

    @XmlAttribute(name = "URL", required = true)
    @NotNull
    public String getFullURL() {
        return this.fullURL;
    }

    @XmlElement(name = "getparam")
    @NotNull
    public List<RequestVariable> getGetParams() {
        return this.getParams;
    }

    @XmlElement(name = "header")
    @NotNull
    public List<RequestVariable> getHeaders() {
        return this.headers;
    }

    @XmlAttribute(name = "tcName", required = true)
    @NotNull
    public String getName() {
        return this.name;
    }

    // This value is extracted from the test case name when it is set via setName(). Not sure it is
    // set when autoloaded from XML file.
    public int getNumber() {
        return this.number;
    }

    @XmlTransient
    public String getQuery() {
        return this.query;
    }

    @XmlAttribute(name = "tcSinkFile", required = true)
    @NotNull
    public String getSinkFile() {
        return this.sinkFile;
    }

    @XmlAttribute(name = "tcSourceFile", required = true)
    @NotNull
    public String getSourceFile() {
        return this.sourceFile;
    }

    @XmlAttribute(name = "tcSourceUIType", required = true)
    @NotNull
    public String getSourceUIType() {
        return this.sourceUIType;
    }

    @XmlAttribute(name = "tcTemplateFile", required = true)
    @NotNull
    public String getTemplateFile() {
        return this.templateFile;
    }

    //    @XmlAttribute(name = "tcType", required = true)
    //    @XmlReadOnly
    //    @NotNull
    public TestCaseType getType() {
        return this.tcType;
    }

    @XmlAttribute(name = "tcUITemplateFile", required = true)
    @NotNull
    public String getUiTemplateFile() {
        return this.uiTemplateFile;
    }

    public boolean isUnverifiable() {
        return getNotAutoverifiableReason() != null;
    }

    @XmlAttribute(name = "tcNotAutoverifiable")
    public String getNotAutoverifiableReason() {
        return this.notAutoverifiableReason;
    }

    @XmlAttribute(name = "tcVulnerable", required = true)
    public boolean isVulnerability() {
        return this.isVulnerability;
    }

    public boolean isSafe() {

        boolean isSafe = true;
        // Bitwise AND is done on all parameters isSafe() values. If ANY of them are unsafe, isSafe
        // set to False.
        for (RequestVariable header : getHeaders()) {
            isSafe &= header.isSafe();
        }

        for (RequestVariable cookie : getCookies()) {
            isSafe &= cookie.isSafe();
        }

        for (RequestVariable getParam : getGetParams()) {
            isSafe &= getParam.isSafe();
        }

        for (RequestVariable formParam : getFormParams()) {
            isSafe &= formParam.isSafe();
        }

        return isSafe;
    }

    public String setAttackSuccessString(String attackSuccessString) {
        return this.attackSuccessString = attackSuccessString;
    }

    public boolean setAttackSuccessStringPresent(boolean attackSuccessStringPresent) {
        return this.attackSuccessStringPresent = attackSuccessStringPresent;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCookies(List<RequestVariable> cookies) {
        this.cookies = cookies;
    }

    public void setDataflowFile(String dataflowFile) {
        this.dataflowFile = dataflowFile;
    }

    public void setFormParams(List<RequestVariable> formParams) {
        this.formParams = formParams;
    }

    public void setFullURL(String fullURL) {
        this.fullURL = fullURL;
    }

    public void setGetParams(List<RequestVariable> getParams) {
        this.getParams = getParams;
    }

    public void setHeaders(List<RequestVariable> headers) {
        this.headers = headers;
    }

    static final Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");

    public void setName(String name) {
        this.name = name;
        // Auto extract the test case number from the name.
        Matcher matcher = lastIntPattern.matcher(name);
        if (matcher.find()) {
            String someNumberStr = matcher.group(1);
            this.number = Integer.parseInt(someNumberStr);
        } else {
            System.out.println(
                    "Warning: TestCaseRequest.setName() invoked with test case name: "
                            + name
                            + " that doesn't end with a test case number.");
        }
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setSinkFile(String sinkFile) {
        this.sinkFile = sinkFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void setSourceUIType(String sourceUIType) {
        this.sourceUIType = sourceUIType;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public void setType(TestCaseType type) {
        this.tcType = type;
    }

    public void setUiTemplateFile(String uiTemplateFile) {
        this.uiTemplateFile = uiTemplateFile;
    }

    public void setNotAutoverifiableReason(String notAutoverifiableReason) {
        this.notAutoverifiableReason = notAutoverifiableReason;
    }

    public void setVulnerability(boolean isVulnerability) {
        this.isVulnerability = isVulnerability;
    }

    public void setSafe(boolean isSafe) {
        for (RequestVariable header : getHeaders()) {
            // setSafe() considers whether attack and safe values exist for this parameter before
            // setting isSafe true or false. So you don't have to check that here.
            header.setSafe(isSafe);
        }
        for (RequestVariable cookie : getCookies()) {
            cookie.setSafe(isSafe);
        }
        for (RequestVariable getParam : getGetParams()) {
            getParam.setSafe(isSafe);
        }
        for (RequestVariable formParam : getFormParams()) {
            formParam.setSafe(isSafe);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + " [category="
                + category
                + ", name="
                + name
                + ", uiTemplateFile="
                + new File(uiTemplateFile).getName()
                + ", templateFile="
                + new File(templateFile).getName()
                + ", sourceFile="
                + sourceFile
                + ", sourceUIType="
                + sourceUIType
                + ", dataflowFile="
                + dataflowFile
                + ", sinkFile="
                + sinkFile
                + ", fullURL="
                + fullURL
                + ", getParams="
                + getParams
                + ", headers="
                + headers
                + ", cookies="
                + cookies
                + ", formParams="
                + formParams
                + ", isUnverifiable="
                + isUnverifiable
                + ", isVulnerability="
                + isVulnerability
                + ", attackSuccessString="
                + attackSuccessString
                + ", isSafe="
                + isSafe()
                + ", query="
                + query
                + ", tcType="
                + tcType
                + "]";
    }
}
