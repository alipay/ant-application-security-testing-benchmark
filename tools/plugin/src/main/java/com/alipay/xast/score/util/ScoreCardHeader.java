package com.alipay.xast.score.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreCardHeader {

    public static List<List<String>> getHeader(){
        ArrayList<List<String>> head = new ArrayList<>();
        head.add(Arrays.asList("文件名"));
        head.add(Arrays.asList("url"));
        head.add(Arrays.asList("评价项"));
        head.add(Arrays.asList("评价项达成条件"));
        head.add(Arrays.asList("存在漏洞"));
        head.add(Arrays.asList("扫描识别为漏洞"));
        return head;
    }
}
