package com.heqing.nosql.neo4j.model;

import java.util.List;
import java.util.Map;

/**
 * 关系
 * @author heqing
 * @date 2018/9/6 14:22
 */
public class Relation {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 标签名称
     */
    private String label;

    /**
     * 一些属性（键值对）
     */
    private Map<String, Object> property;

    /**
     * 从某个节点
     */
    private Node fromNode;

    /**
     * 到某个节点
     */
    private Node toNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }
}
