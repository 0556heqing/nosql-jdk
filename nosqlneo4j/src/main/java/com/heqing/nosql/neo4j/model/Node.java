package com.heqing.nosql.neo4j.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 节点
 * @author heqing
 * @date 2018/8/27 19:11
 */
public class Node {

    /**
     * 节点名称
     */
    private String name = "";

    /**
     * 一个或多个节点标签名称
     */
    private String label = "";

    /**
     * 一些属性（键值对）
     */
    private Map<String, Object> property = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, Object> getProperty() {
        return property;
    }

    public void setProperty(Map<String, Object> property) {
        this.property = property;
    }
}
