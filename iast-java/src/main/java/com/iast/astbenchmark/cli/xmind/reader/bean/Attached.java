package com.iast.astbenchmark.cli.xmind.reader.bean;


import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-03-24 11:24:27
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Attached {
    private String id;
    private String title;
    private Notes notes;
    private List<Attached> expectedResultList; // 如果节点是步骤，则该节点为预期结果列表
    private Children children;
    private MarkerRefs markerrefs;
    private List<MarkerId> markers;
    private Image image;
}