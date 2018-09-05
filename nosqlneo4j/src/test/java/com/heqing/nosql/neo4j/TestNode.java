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
        property.put("name", "zhangsan");
        property.put("age", 18);
        property.put("time", new Date());
        property.put("isMan", true);

        // 创建节点
        Node student = new Node();
        student.setName("s");
        List<String> labelList = new ArrayList<>();
        labelList.add("Student");
        student.setProperty(property);
        Neo4jUtil.getNode().createNode(student);
    }

    @Test
    public void listNode() {
        String nodeName = "s", labelName = "Student";

        // 查询条件
        String param = "";
        List<Object> nameList = new ArrayList<>();
        nameList.add("zhangsan");
        nameList.add("lisi");
        String name = QueryUtil.in(nodeName, "name", nameList);
        String maxAge = QueryUtil.lte(nodeName, "age", 17D);
        String minAge = QueryUtil.gt(nodeName, "age", 16D);
        String isMan = QueryUtil.eq(nodeName, "isMan", true);
        String and = QueryUtil.and(name, minAge, maxAge);
        String or = QueryUtil.or(and, isMan);
        param = or;

        // 排序条件
        Map<String, Integer> sort = new HashMap<>();
        sort.put("isMan", 1);
        sort.put("age", -1);

        List<String> labelList = new ArrayList<>();
        labelList.add("Student");

        // 查询节点
        Neo4jUtil.getNode().listNode(nodeName, labelList, param, sort, 0, 2, null);

        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("name", "n");
        propertyMap.put("time", "t");
        Neo4jUtil.getNode().listNode(nodeName, labelList, param, sort, 0, 2, propertyMap);
    }

    @Test
    public void updateNodeProperty() {
        // 删除一个节点的属性
        Map<String, Object> property = new HashMap<>();
        property.put("name", "wangwu");
        property.put("age", 17);
        property.put("time", new Date());
        property.put("isMan", false);

        Node node = new Node();
        node.setName("s");
        List<String> labelList = new ArrayList<>();
        labelList.add("Student");
        node.setProperty(property);

        String param = QueryUtil.eq(node.getName(), "name", "wangwu");
        Neo4jUtil.getNode().updateNodeProperty(node, param);
    }

    @Test
    public void deleteNode() {
        String name = "s";
        List<String> labelList = new ArrayList<>();
        labelList.add("Student");
        // 删除一个节点
        String param = QueryUtil.eq(name, "name", "yangliu");

        Node node = new Node();
        node.setName(name);
        node.setLabels(labelList);
        Neo4jUtil.getNode().deleteNode(node, param);
    }

    @Test
    public void deleteNodeProperty() {
        // 删除一个节点的属性
        String name = "s";
        String param = QueryUtil.eq(name, "name", "wangwu");
        List<String> labelList = new ArrayList<>();
        labelList.add("Student");

        List<String> propertyNameList = new ArrayList<>();
        propertyNameList.add("time");
        propertyNameList.add("class");

        Node node = new Node();
        node.setName(name);
        node.setLabels(labelList);
        Neo4jUtil.getNode().deleteNodeProperty(node, param, propertyNameList);
    }

    @Test
    public void deleteNodeLabel() {
        // 删除一个节点的属性
        String name = "s";
        String param = QueryUtil.eq(name, "name", "唐老鸭");
        List<String> labelList = new ArrayList<>();
        labelList.add("Student");

        List<String> labelNameList = new ArrayList<>();
        labelNameList.add("Student");
        labelNameList.add("Person");

        Node node = new Node();
        node.setName(name);
        node.setLabels(labelList);
        Neo4jUtil.getNode().deleteNodeLabel(node, param, labelNameList);
    }

    @Test
    public void addNodeLabel() {
        // 删除一个节点的属性
        String name = "s";
        String param = QueryUtil.eq(name, "name", "唐老鸭");
        List<String> labelList = new ArrayList<>();
        labelList.add("Student");

        List<String> labelNameList = new ArrayList<>();
        labelNameList.add("Student");
        labelNameList.add("Person");

        Node node = new Node();
        node.setName(name);
        node.setLabels(labelList);
        Neo4jUtil.getNode().addNodeLabel(node, param, labelNameList);
    }
}
