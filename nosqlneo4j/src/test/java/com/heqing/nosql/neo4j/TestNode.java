package com.heqing.nosql.neo4j;

import com.alibaba.fastjson.JSON;
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
        // 创建节点
        Node node = new Node();
        node.setName("f");
        // 标签
        List<String> labelList = new ArrayList<>();
        labelList.add("Factions");
        labelList.add("China");
        node.setLabels(labelList);

        Map<String, Object> property = new HashMap<>();
        property.put("name", "盖聂");
        property.put("describe", "剑圣");
        property.put("factions", "纵横家");
        property.put("isMan", true);
        property.put("age", 28);
        node.setProperty(property);
        Node result = Neo4jUtil.getNode().createNode(node);
        System.out.println("---> createNode : " + JSON.toJSONString(result));

        node = new Node();
        node.setName("f");
        // 标签
        labelList = new ArrayList<>();
        labelList.add("Factions");
        labelList.add("China");
        node.setLabels(labelList);

        property = new HashMap<>();
        property.put("name", "卫庄");
        property.put("describe", "流沙首领");
        property.put("factions", "纵横家");
        property.put("isMan", true);
        property.put("age", 27);
        node.setProperty(property);
        result = Neo4jUtil.getNode().createNode(node);
        System.out.println("---> createNode : " + JSON.toJSONString(result));

        node = new Node();
        node.setName("f");
        // 标签
        labelList = new ArrayList<>();
        labelList.add("Factions");
        labelList.add("China");
        node.setLabels(labelList);

        property = new HashMap<>();
        property.put("name", "端木蓉");
        property.put("factions", "药家");
        property.put("isMan", false);
        property.put("age", 25);
        node.setProperty(property);
        result = Neo4jUtil.getNode().createNode(node);
        System.out.println("---> createNode : " + JSON.toJSONString(result));
    }

    @Test
    public void listNode() {
        String nodeName = "f";

        List<String> labelList = new ArrayList<>();
        labelList.add("Factions");

        // 查询条件
        String param = "";
        List<Object> nameList = new ArrayList<>();
        nameList.add("盖聂");
        nameList.add("端木蓉");
        String name = QueryUtil.in(nodeName, "name", nameList);
        String maxAge = QueryUtil.lte(nodeName, "age", 30D);
        String minAge = QueryUtil.gt(nodeName, "age", 25D);
        String isMan = QueryUtil.eq(nodeName, "isMan", true);
        String and = QueryUtil.and(name, minAge, maxAge);
        String or = QueryUtil.or(and, isMan);
        param = or;

        // 排序条件
        Map<String, Integer> sort = new HashMap<>();
        sort.put("isMan", 1);
        sort.put("age", -1);

        // 查询节点
        List<Node> nodeList = Neo4jUtil.getNode().listNode(nodeName, labelList, param, sort, 0, 2, null);
        System.out.println("---> listNode1 = "+ JSON.toJSONString(nodeList));

        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("name", "n");
        propertyMap.put("age", "");
        nodeList =  Neo4jUtil.getNode().listNode(nodeName, labelList, param, sort, 0, 2, propertyMap);
        System.out.println("---> listNode2 = "+ JSON.toJSONString(nodeList));
    }

    @Test
    public void updateNodeProperty() {
        Map<String, Object> property = new HashMap<>();
        property.put("name", "端木蓉");
        property.put("factions", "医家");
        property.put("isMan", false);
        property.put("age", 25);

        Node node = new Node();
        node.setName("f");
        List<String> labelList = new ArrayList<>();
        labelList.add("Factions");
        node.setProperty(property);

        String param = QueryUtil.eq(node.getName(), "name", "端木蓉");
        Node result = Neo4jUtil.getNode().updateNodeProperty(node, param);
        System.out.println("---> updateNodeProperty : " + JSON.toJSONString(result));
    }

    @Test
    public void deleteNodeProperty() {
        // 删除一个节点的属性
        List<String> labelList = new ArrayList<>();
        labelList.add("Factions");
        Node node = new Node();
        node.setName("f");
        node.setLabels(labelList);

        List<String> propertyNameList = new ArrayList<>();
        propertyNameList.add("isMan");

        String param = QueryUtil.eq(node.getName(), "name", "端木蓉");

        Node result = Neo4jUtil.getNode().deleteNodeProperty(node, param, propertyNameList);
        System.out.println("---> deleteNodeProperty : " + JSON.toJSONString(result));
    }

    @Test
    public void deleteNode() {
        Node node = new Node();
        node.setName("f");
        // 标签
        List<String> labelList = new ArrayList<>();
        labelList.add("Factions");
        node.setLabels(labelList);

        // 删除一个节点
        String param = QueryUtil.eq(node.getName(), "name", "卫庄");
        Neo4jUtil.getNode().deleteNode(node, param);
    }

    @Test
    public void addNodeLabel() {
        // 节点
        Node node = new Node();
        node.setName("f");
        // 标签
        List<String> labelList = new ArrayList<>();
        labelList.add("Factions");
        node.setLabels(labelList);

        List<String> labelNameList = new ArrayList<>();
        labelNameList.add("Qin");

        String param = QueryUtil.eq(node.getName(), "name", "盖聂");

        Node result =  Neo4jUtil.getNode().addNodeLabel(node, param, labelNameList);
        System.out.println("---> addNodeLabel : " + JSON.toJSONString(result));
    }

    @Test
    public void deleteNodeLabel() {
        // 删除一个节点的属性
        List<String> labelList = new ArrayList<>();
        labelList.add("Factions");

        Node node = new Node();
        node.setName("f");
        node.setLabels(labelList);

        List<String> labelNameList = new ArrayList<>();
        labelNameList.add("Qin");
        String param = QueryUtil.eq(node.getName(), "name", "盖聂");

        Node result = Neo4jUtil.getNode().deleteNodeLabel(node, param, labelNameList);
        System.out.println("---> deleteNodeProperty : " + JSON.toJSONString(result));
    }

}
