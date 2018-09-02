package com.heqing.nosql.neo4j;

import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.model.RelationShip;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author heqing
 * @date 2018/9/2 16:17
 */
public class TestRelationShip {

    @Test
    public void createRelationShip() {
        Map<String, Object> book1 = new HashMap<>();
        book1.put("name", "西游记");
        book1.put("page", 1000);
        Node formNode = new Node();
        formNode.setName("book1");
        formNode.setLabel("fb1");
        formNode.setProperty(book1);

        Map<String, Object> book2 = new HashMap<>();
        book2.put("name", "红楼梦");
        book2.put("time", new Date());
        Node toNode = new Node();
        toNode.setName("book2");
        toNode.setLabel("fb2");
        toNode.setProperty(book2);

        Node test = new Node();
        test.setName("test");
        Map<String, Object> book = new HashMap<>();
        book.put("name", "名著");
        book.put("isFamous", true);
        book.put("test", test);

        RelationShip relationShip = new RelationShip();
        relationShip.setName("LIKES");
        relationShip.setLabel("like");
        relationShip.setProperty(book);
        relationShip.setFromNode(formNode);
        relationShip.setToNode(toNode);

        Neo4jUtil.getRelationShip().createRelationShip(relationShip);
    }

    @Test
    public void listRelationShipByLabel() {
        String shipLabel = "喜欢";
        Neo4jUtil.getRelationShip().listRelationShipByLabel(shipLabel);
    }

    @Test
    public void deleteRelationShip() {
        Node formNode = new Node();
        formNode.setName("book1");
        formNode.setLabel("fb1");

        Node toNode = new Node();
        toNode.setName("book2");
        toNode.setLabel("fb2");

        RelationShip relationShip = new RelationShip();
        relationShip.setName("LIKES");
        relationShip.setLabel("like");
        relationShip.setFromNode(formNode);
        relationShip.setToNode(toNode);

        Neo4jUtil.getRelationShip().deleteRelationShip(relationShip);
    }
}
