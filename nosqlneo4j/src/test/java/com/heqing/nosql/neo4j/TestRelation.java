package com.heqing.nosql.neo4j;

import com.alibaba.fastjson.JSON;
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
    public void createRelation() {
        Map<String, Object> property = null;
        List<String> labelList = new ArrayList<>();
        labelList.add("Factions");

        Node p1 = new Node();
        p1.setName("p1");
        p1.setLabels(labelList);
        property = new HashMap<>();
        property.put("name", "盖聂");
        property.put("describe", "剑圣");
        property.put("factions", "纵横家");
        property.put("age", 28);
        p1.setProperty(property);

        Node p2 = new Node();
        p2.setName("p2");
        p2.setLabels(labelList);
        property = new HashMap<>();
        property.put("name", "卫庄");
        property.put("describe", "流沙首领");
        property.put("factions", "纵横家");
        property.put("age", 27);
        p2.setProperty(property);

        Relation relation = new Relation();
        relation.setName("f");
        relation.setLabel("同门");
        relation.setFromNode(p1);
        relation.setToNode(p2);
        property = new HashMap<>();
        property.put("teacher", "鬼谷子");
        relation.setProperty(property);

        Relation result =  Neo4jUtil.getRelationShip().createRelation(relation);
        System.out.println("---> createRelation : " + JSON.toJSONString(result));
    }

    @Test
    public void listRelation() {
        Node p1 = new Node();
        p1.setName("p1");

        Node p2 = new Node();
        p2.setName("p2");

        Relation relation = new Relation();
        relation.setName("f");
        relation.setLabel("同门");
        relation.setFromNode(p1);
        relation.setToNode(p2);

        String f1 = QueryUtil.eq(p1.getName(), "name", "盖聂");
        String f2 = QueryUtil.eq(p2.getName(), 70L);
        String param = QueryUtil.or(f1, f2);

        List<Relation> result = Neo4jUtil.getRelationShip().listRelation(relation, param, 0, 10);
        System.out.println("---> createRelation : " + JSON.toJSONString(result));
    }

    @Test
    public void updateRelation() {
        Map<String, Object> property = null;
        List<String> labelList = new ArrayList<>();
        labelList.add("Factions");

        Node p1 = new Node();
        p1.setName("p1");
        p1.setLabels(labelList);
        property = new HashMap<>();
        property.put("name", "盖聂");
        property.put("describe", "剑圣");
        property.put("factions", "纵横家");
        property.put("arms", "渊虹");
        property.put("age", 28);
        p1.setProperty(property);

        Node p2 = new Node();
        p2.setName("p2");
        p2.setLabels(labelList);
        property = new HashMap<>();
        property.put("name", "卫庄");
        property.put("describe", "流沙首领");
        property.put("factions", "纵横家");
        property.put("arms", "鲨齿");
        property.put("age", 27);
        p2.setProperty(property);

        Relation relation = new Relation();
        relation.setName("f");
        relation.setLabel("同门");
        relation.setFromNode(p1);
        relation.setToNode(p2);
        property = new HashMap<>();
        property.put("teacher", "鬼谷子");
        property.put("describe", "苍生涂涂,天下缭燎,诸子百家,唯我纵横");
        relation.setProperty(property);

        String f1 = QueryUtil.eq(p1.getName(), "name", "盖聂");
        String f2 = QueryUtil.eq(p2.getName(), 74L);
        String param = QueryUtil.and(f1, f2);

        Relation result = Neo4jUtil.getRelationShip().updateRelation(relation, param);

        System.out.println("---> updateRelation : " + JSON.toJSONString(result));
    }

    @Test
    public void deleteRelation() {
        Node p1 = new Node();
        p1.setName("p1");

        Node p2 = new Node();
        p2.setName("p2");

        Relation relation = new Relation();
        relation.setName("f");
        relation.setLabel("同门");
        relation.setFromNode(p1);
        relation.setToNode(p2);

        String f1 = QueryUtil.eq(p1.getName(), "name", "盖聂");
        String f2 = QueryUtil.eq(p2.getName(), 70L);
        String param = QueryUtil.and(f1, f2);

        Neo4jUtil.getRelationShip().deleteRelation(relation, param);
    }
}
