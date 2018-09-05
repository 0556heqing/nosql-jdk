package com.heqing.nosql.neo4j;

import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.model.RelationShip;
import org.junit.Test;

import java.util.*;

/**
 * @author heqing
 * @date 2018/9/2 16:17
 */
public class TestRelationShip {

    @Test
    public void createRelationShip() {
        Map<String, Object> property = null;
        List<String> labelList = new ArrayList<>();
        labelList.add("Student");

        Node student1 = new Node();
        student1.setName("s1");
        student1.setLabels(labelList);
        property = new HashMap<>();
        property.put("name", "唐老鸭");
        property.put("age", 18);
        property.put("time", new Date());
        property.put("isMan", true);
        student1.setProperty(property);

        Node student2 = new Node();
        student2.setName("s2");
        student2.setLabels(labelList);
        property = new HashMap<>();
        property.put("name", "米老鼠");
        property.put("age", 17);
        property.put("isMan", false);
        student2.setProperty(property);

        RelationShip relationShip = new RelationShip();
        relationShip.setName("like");
        relationShip.setLabel("LIKE");
        relationShip.setFromNode(student1);
        relationShip.setToNode(student2);

        Neo4jUtil.getRelationShip().createRelationShip(relationShip);
    }
//
//    @Test
//    public void listRelationShipByLabel() {
//        String shipLabel = "喜欢";
//        Neo4jUtil.getRelationShip().listRelationShipByLabel(shipLabel);
//    }
//
//    @Test
//    public void deleteRelationShip() {
//        Node formNode = new Node();
//        formNode.setName("book1");
//        formNode.setLabel("fb1");
//
//        Node toNode = new Node();
//        toNode.setName("book2");
//        toNode.setLabel("fb2");
//
//        RelationShip relationShip = new RelationShip();
//        relationShip.setName("LIKES");
//        relationShip.setLabel("like");
//        relationShip.setFromNode(formNode);
//        relationShip.setToNode(toNode);
//
//        Neo4jUtil.getRelationShip().deleteRelationShip(relationShip);
//    }
}
