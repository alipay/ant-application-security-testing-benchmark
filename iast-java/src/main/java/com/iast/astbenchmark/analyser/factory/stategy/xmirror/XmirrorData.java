package com.iast.astbenchmark.analyser.factory.stategy.xmirror;

import java.util.List;

public class XmirrorData {

    private List<XmirrorRecord> records;
    private int                 total;
    private int size;
    private int current;
    private List<String> orders;
    private boolean optimizeCountSql;
    private boolean searchCount;
    private String countId;
    private String maxLimit;
    private int pages;
    public void setRecords(List<XmirrorRecord> records) {
        this.records = records;
    }
    public List<XmirrorRecord> getRecords() {
        return records;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    public int getCurrent() {
        return current;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }
    public List<String> getOrders() {
        return orders;
    }

    public void setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }
    public boolean getOptimizeCountSql() {
        return optimizeCountSql;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }
    public boolean getSearchCount() {
        return searchCount;
    }

    public void setCountId(String countId) {
        this.countId = countId;
    }
    public String getCountId() {
        return countId;
    }

    public void setMaxLimit(String maxLimit) {
        this.maxLimit = maxLimit;
    }
    public String getMaxLimit() {
        return maxLimit;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
    public int getPages() {
        return pages;
    }

}