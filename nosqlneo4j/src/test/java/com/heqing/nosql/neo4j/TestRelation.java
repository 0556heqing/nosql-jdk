package com.heqing.nosql.neo4j;

import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.model.Relation;
import org.junit.Test;

import java.util.*;

/**
 * @author heqing
 * @date 2018/9/2 16:17
 */
public class TestRelation {

    @Test
    public void createRelationShip() {
        Map<String, Object> property = null;
        List<String> labelList = new ArrayList<>();
        labelList.add("Student");

        Node student1 = new Node();
        student1.setName("s1");
        student1.setLabels(labelList);
        property = new HashMap<>();
        property.put("name", "唐老鸭11");
        property.put("age", 18);
        property.put("time", new Date());
        property.put("isMan", true);
        student1.setProperty(property);

        Node student2 = new Node();
        student2.setName("s2");
        student2.setLabels(labelList);
        property = new HashMap<>();
        property.put("name", "米老鼠11");
        property.put("age", 17);
        property.put("isMan", false);
        student2.setProperty(property);

        List<String> labels = new ArrayList<>();
        labels.add("NOT_LIKE");
        Relation relation = new Relation();
        relation.setName("like");
        relation.setLabels(labels);
        relation.setFromNode(student1);
        relation.setToNode(student2);
        property = new HashMap<>();
        property.put("cause", "相隔太远");
        property.put("time", new Date());
        property.put("msg", "再见，我的爱");
        relation.setProperty(property);

        Neo4jUtil.getRelationShip().createRelation(relation);
    }

    @Test
    public void listRelation() {
        List<String> labels = new ArrayList<>();
        labels.add("NOT_LIKE");

        Node formNode = new Node();
        formNode.setName("form");
        Node toNode = new Node();
        toNode.setName("to");

        Relation relation = new Relation();
        relation.setName("r");
        relation.setLabels(labels);
        relation.setFromNode(formNode);
        relation.setToNode(toNode);

        String param = QueryUtil.eq(toNode.getName(), "name", "唐老鸭");

        Neo4jUtil.getRelationShip().listRelation(relation, param, 0, 10);
    }

    @Test
    public void updateRelation() {
        List<String> labels = new ArrayList<>();
        labels.add("NOT_LIKE");

        Node formNode = new Node();
        formNode.setName("form");
        Map<String, Object> property = new HashMap<>();
        property.put("age", 18);
        formNode.setProperty(property);
        Node toNode = new Node();
        toNode.setName("to");

        Relation relation = new Relation();
        relation.setName("r");
        relation.setLabels(labels);
        relation.setFromNode(formNode);
        relation.setToNode(toNode);
        property = new HashMap<>();
        property.put("year", "2018");
        relation.setProperty(property);

        String param = QueryUtil.eq(formNode.getName(), "name", "唐老鸭");

        Neo4jUtil.getRelationShip().updateRelation(relation, param);
    }

    @Test
    public void deleteRelation() {
        List<String> labels = new ArrayList<>();
        labels.add("NOT_LIKE");

        Node formNode = new Node();
        formNode.setName("form");
        Node toNode = new Node();
        toNode.setName("to");

        Relation relation = new Relation();
        relation.setName("r");
        relation.setLabels(labels);
        relation.setFromNode(formNode);
        relation.setToNode(toNode);

        String param = QueryUtil.eq(formNode.getName(), "name", "唐老鸭");
        Neo4jUtil.getRelationShip().deleteRelation(relation, param);
    }
}
