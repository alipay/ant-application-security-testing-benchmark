package com.iast.astbenchmark.cases.bean.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "message")
public class TicketRequest {
    public String phase;

    @JacksonXmlProperty(localName = "phase")
    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
    // @JacksonXmlElementWrapper(localName ="orderlist")
    // @JacksonXmlProperty(localName ="order")
    // private List<OrderRequest> orderList;
    //
    // public List<OrderRequest> getOrderList() {
    // return orderList;
    // }
    //
    // public void setOrderList(List<OrderRequest> orderList) {
    // this.orderList = orderList;
    // }

}