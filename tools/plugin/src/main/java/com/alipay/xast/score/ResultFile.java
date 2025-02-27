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
 * @author Sascha Knoop
 * @created 2022
 */
package com.alipay.xast.score;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ResultFile {
    private final byte[] rawContent;
    private final String filename;
    private final File originalFile;
    private JSONObject contentAsJson;
    private Document contentAsXml;

    public ResultFile(File fileToParse) throws IOException {
        this(fileToParse, readFileContent(fileToParse));
    }

    public ResultFile(String filename, String content) throws IOException {
        this(filename, content.getBytes());
    }

    public ResultFile(String filename, byte[] rawContent) throws IOException {
        this(new File(filename), rawContent);
    }

    public ResultFile(File fileToParse, byte[] rawContent) throws IOException {
        this.rawContent = rawContent;
        originalFile = fileToParse;
        filename = originalFile.getName();
        parseJson();
        parseXml();
    }

    private String removeBom(byte[] rawContent) {
        String s = new String(rawContent, StandardCharsets.UTF_8);

        if (s.startsWith("\uFEFF")) {
            return s.substring(1);
        }

        return s;
    }

    private static byte[] readFileContent(File fileToParse) throws IOException {
        return Files.readAllBytes(Paths.get(fileToParse.getPath()));
    }

    private void parseJson() {
        try {
            contentAsJson = new JSONObject(removeBom(rawContent));
        } catch (Exception ignored) {
            // No JSON
        }
    }

    private void parseXml() {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            // Prevent XXE = Note, disabling DTDs entirely breaks the parsing of some XML files,
            // like a Burp results file, so have to use the alternate defense.
            // dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            docBuilderFactory.setFeature(
                    "http://xml.org/sax/features/external-general-entities", false);
            docBuilderFactory.setFeature(
                    "http://xml.org/sax/features/external-parameter-entities", false);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            docBuilder.setErrorHandler(new DefaultHandler());
            InputSource is = new InputSource(new StringReader(this.content()));
            this.contentAsXml = docBuilder.parse(is);
        } catch (Exception ignored) {
            // No XML
        }
    }

    public String filename() {
        return filename;
    }

    public boolean isJson() {
        return contentAsJson != null;
    }

    public boolean isXml() {
        return contentAsXml != null;
    }

    public JSONObject json() {
        return contentAsJson;
    }

    public String content() {
        return removeBom(rawContent);
    }

    public File file() {
        return originalFile;
    }

    /**
     * Read the specified line of the provided file. Returns empty string if the given file does not
     * have as many lines.
     */
    public String line(int lineNum) {
        List<String> lines = Arrays.asList(removeBom(rawContent).split("\n"));

        if (lineNum >= lines.size()) {
            return "";
        }

        return lines.get(lineNum);
    }

    public List<String> lines() {
        return new ArrayList<>();
    }

    public Document xml() {
        return contentAsXml;
    }

    public Element xmlRootNode() {
        return xml().getDocumentElement();
    }

    public String xmlRootNodeName() {
        return isXml() ? xmlRootNode().getNodeName() : "";
    }

    /**
     * Extracts a file from a packed ResultFile.
     *
     * @return
     */
    public ResultFile extract(String zipPath) {
        try (ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(rawContent))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                if (entry.getName().equals(zipPath)) {
                    return readFileFromZip(zipPath, zipIn);
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("ZipFile does not contain " + zipPath);
    }

    private ResultFile readFileFromZip(String zipPath, ZipInputStream zipIn) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            final byte[] buf = new byte[1024];
            int length;
            while ((length = zipIn.read(buf, 0, buf.length)) >= 0) {
                bos.write(buf, 0, length);
            }
            return new ResultFile(zipPath, bos.toByteArray());
        }
    }
}
