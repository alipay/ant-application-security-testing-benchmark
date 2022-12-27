package com.iast.astbenchmark.analyser.factory.stategy.seeker;

import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;
import lombok.Data;

@Data
public class SeekerCollectBaseData  extends BaseOriginalDataBean {
    private String Owner;
    private String ProjectKey;
    private String ItemKey;
    private String CheckerKey;
    private String VulnerabilityName;
    private String Severity;
    private String ticketUrls;
    private String URL;
    private String SourceName;
    private String SourceType;
    private String CodeLocation;
    private String VerificationTag;
    private int DetectionCount;
    private String FirstDetectionTime;
    private String LastDetectionTime;
    private String Status;
    private String OWASP2013;
    private String OWASP2017;
    private String GDPR;
    private String CAPEC;
    private String LastDetectionURL;
    private String SeekerServerLink;
    private String CustomTags;
    private String LatestVersion;
    private String CodeLocationType;
    private String OWASP2021;
}
