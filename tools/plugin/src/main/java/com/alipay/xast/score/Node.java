/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.xast.score;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author lyh01158086@antgroup.com
 * @version Node.java, v 0.1 2024年06月12日 下午7:22 lyh01158086@antgroup.com
 */
public class Node implements Serializable {

    private static final long serialVersionUID = -6522623941445549737L;

    private String id;

    private String parentid;

    private String topic;

    public Node(String id, String parentid, String topic) {
        this.id = id;
        this.parentid = parentid;
        this.topic = topic;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("parentid", this.parentid);
        jsonObject.put("topic", this.topic);
        return jsonObject;
    }

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>parentid</tt>.
     *
     * @return property value of parentid
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * Setter method for property <tt>parentid</tt>.
     *
     * @param parentid value to be assigned to property parentid
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * Getter method for property <tt>topic</tt>.
     *
     * @return property value of topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Setter method for property <tt>topic</tt>.
     *
     * @param topic value to be assigned to property topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }
}