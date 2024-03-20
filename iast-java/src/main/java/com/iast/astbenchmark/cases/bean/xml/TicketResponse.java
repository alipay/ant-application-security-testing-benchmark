package com.iast.astbenchmark.cases.bean.xml;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "message")
public class TicketResponse {

    private List<OrderResponse> orderList;

    @JacksonXmlElementWrapper(localName = "orderlist")
    @JacksonXmlProperty(localName = "order")
    public List<OrderResponse> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderResponse> orderList) {
        this.orderList = orderList;
    }
}
