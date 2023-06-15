package com.alipay.antbenchmark.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class CrawlerSingle {

    private static final Logger log = LoggerFactory.getLogger("CrawlerSingle");

    boolean supportAjax = false;
    boolean supportHtml = false;
    HashSet<Integer> nowCount = new HashSet<Integer>();

    long total = countYaml();

    public void clearNowCount() {
        this.nowCount.clear();
    }

    public int getNowCount() {
        return nowCount.size();
    }

    public boolean isSupportAjax() {
        return supportAjax;
    }

    public boolean isSupportHtml() {
        return supportHtml;
    }

    public void addCaseNum(int caseNum) {
        this.nowCount.add(caseNum);
    }

    public void setSupportAjax(boolean supportAjax) {
        this.supportAjax = supportAjax;
    }

    public void setSupportHtml(boolean supportHtml) {
        this.supportHtml = supportHtml;
    }

    public long getTotal(){
        return total;
    }
    private static CrawlerSingle crawlerSingle;

    private CrawlerSingle() throws MalformedURLException, URISyntaxException {
    }

    public static CrawlerSingle getInstance() throws MalformedURLException, URISyntaxException {
        if (crawlerSingle == null) {
            crawlerSingle = new CrawlerSingle();
        }
        return crawlerSingle;

    }

    public long countYaml() throws URISyntaxException, MalformedURLException {
        URL url = this.getClass().getResource("/scorecard/").toURI().toURL();
        //jar内部
        if (SystemConsts.PROTOCOL_JAR.equals(url.getProtocol())) {
            log.info("getTotal . In jar");
            String path = url.getPath();
            String jarPath = path.substring(5, path.indexOf("!"));
            try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8.name()))) {
                log.info("getTotal .  the jarPath:{}", jarPath);
                Enumeration<JarEntry> entries = jar.entries();
                long total = 0;
                //遍历资源
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    // 非XML
                    URL resourceurl = Thread.currentThread().getContextClassLoader().getResource(name);
                    log.info("getTotal no yaml . Inside the Jar! the resourceurl:{} ,the name:{}", resourceurl, name);
                    if (!resourceurl.toString().endsWith(".yaml") || !resourceurl.toString().contains("/scorecard/")) {
                        continue;
                    }
                    total++;
                }
                log.info("getTotal .  the total:{}", total);
                return total;
            } catch (IOException e) {
                log.error("getTotal error . the exception:{}", e.getMessage());
            }
        }
        log.info("getTotal . In IDE");
        String filePath = (new File("")).getAbsolutePath() + "/src/main/resources/scorecard/";
        File f = new File(filePath);
        String[] pathnames = f.list();
        long total = pathnames.length;
        log.info("getTotal . the total:{}", total);
        return total;

    }

}
