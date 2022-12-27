package com.iast.astbenchmark.common.utils;

import cn.hutool.json.JSONUtil;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.iast.astbenchmark.analyser.bean.CaseTargetItemBean;
import com.google.common.collect.Lists;

import java.util.List;

public class GenerateTagetJson {
    public static void main(String[] args) {
        //CaseTargetBean
        for (int i = 125; i < 138; i++) {
            CaseTargetBean targetBean = new CaseTargetBean();
            targetBean.setCaseNo("aTaintCase00"+i);
            targetBean.setCaseType("T004");
            targetBean.setCaseDesc("");
            CaseTargetItemBean itemBean = new CaseTargetItemBean();
            itemBean.setTag("aTaintCase00"+i);
            itemBean.setResult(true);
            CaseTargetItemBean itemBean2 = new CaseTargetItemBean();
            itemBean2.setTag("aTaintCase00"+i+"_2");
            itemBean2.setResult(false);
            List<CaseTargetItemBean> itemBeanList = Lists.newArrayList();
            itemBeanList.add(itemBean);
            itemBeanList.add(itemBean2);
            targetBean.setData(itemBeanList);
            System.out.println(JSONUtil.toJsonStr(targetBean)+",");
        }
    }
}
