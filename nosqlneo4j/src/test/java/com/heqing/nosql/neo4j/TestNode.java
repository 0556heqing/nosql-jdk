package com.heqing.nosql.neo4j;

import com.heqing.nosql.neo4j.model.Node;
import org.junit.Test;

import java.util.*;

/**
 * @author heqing
 * @date 2018/8/27 11:22
 */
public class TestNode {

    @Test
    public void createNode() {
        Map<String, Object> property = new HashMap<>();
        property.put("name", "heqing");
        property.put("age", 28);
        property.put("time", new Date());
        property.put("isMan", true);

        // 创建节点
        Node node = new Node();
        node.setName("createNode");
        node.setLabel("Test");
        node.setProperty(property);
        Neo4jUtil.getNode().createNode(node);
    }

    @Test
    public void createLabelByNodeName() {
        // 为节点创建多个标签
        List<String> labels = new ArrayList<>();
        labels.add("Test");
        labels.add("HQ");
        Neo4jUtil.getNode().createLabelByNodeName("createNode", labels);
    }

    @Test
    public void listNodeByName() {
        Node node = new Node();
        node.setName("createNode");
        node.setLabel("Test");

        // 根据标签获取节点属性
        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("name", "name");
        propertyMap.put("age", "age");
        Neo4jUtil.getNode().listNodeByLabel(node, propertyMap);
    }

    @Test
    public void deleteNode() {
        // 删除一个节点
        Node node = new Node();
        node.setName("createNode");
        node.setLabel("Test");
        Neo4jUtil.getNode().deleteNode(node);
    }

    @Test
    public void removeNodeProperty() {
        // 删除一个节点的属性
        Map<String, Object> property = new HashMap<>();
        property.put("name", "heqing");
        Node node = new Node();
        node.setName("createNode");
        node.setLabel("Test");
//        node.setProperty(property);

        List<String> propertyNameList = new ArrayList<>();
        propertyNameList.add("name");
        Neo4jUtil.getNode().removeNodeProperty(node, propertyNameList);
    }

    @Test
    public void removeNodeLabel() {
        // 删除一个节点的属性
        Map<String, Object> property = new HashMap<>();
        property.put("name", "heqing");
        Node node = new Node();
        node.setName("createNode");
        node.setLabel("Test");
        node.setProperty(property);

        List<String> labelNameList = new ArrayList<>();
        labelNameList.add("name");
        Neo4jUtil.getNode().removeNodeLabel(node, labelNameList);
    }

    @Test
    public void updateNode() {
        // 删除一个节点的属性
        Map<String, Object> property = new HashMap<>();
        property.put("name", "heqing");
        Node node = new Node();
        node.setName("createNode");
        node.setLabel("Test");
        node.setProperty(property);

        Neo4jUtil.getNode().updateNode(node);
    }
}
