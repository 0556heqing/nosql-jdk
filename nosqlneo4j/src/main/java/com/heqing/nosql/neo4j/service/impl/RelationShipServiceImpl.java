package com.heqing.nosql.neo4j.service.impl;

import com.heqing.nosql.neo4j.Neo4jUtil;
import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.model.RelationShip;
import com.heqing.nosql.neo4j.service.RelationShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关系
 * @author heqing
 * @date 2018/9/2 16:12
 */
public class RelationShipServiceImpl implements RelationShipService {

    private static final Logger logger = LoggerFactory.getLogger(RelationShipServiceImpl.class);

    @Override
    public void createRelationShip(RelationShip relationShip) {
        if(relationShip.getFromNode() != null && relationShip.getToNode() != null) {
            Map<String, Object> property = new HashMap<>(16);

            // 组装From Node信息
            StringBuilder fromNodeCql = new StringBuilder();
            fromNodeCql.append("(");
            List<Node> fromNode = Neo4jUtil.getNode().listNodeByLabel(relationShip.getFromNode(), null);
            if(fromNode.size() > 0) {
                fromNodeCql.append(relationShip.getFromNode().getName());
            } else {
                fromNodeCql.append(relationShip.getFromNode().getLabel());
                fromNodeCql.append(":" + relationShip.getFromNode().getName());
                property = relationShip.getFromNode().getProperty();
                if(property != null && property.size() > 0) {
                    fromNodeCql.append(Neo4jUtil.mapToProperty(property));
                }
            }
            fromNodeCql.append(")");


            // 组装From Node信息
            StringBuilder toNodeCql = new StringBuilder();
            toNodeCql.append("(");
            List<Node> toNode = Neo4jUtil.getNode().listNodeByLabel(relationShip.getToNode(), null);
            if(toNode.size() > 0) {
                toNodeCql.append(relationShip.getToNode().getName());
            } else {
                toNodeCql.append(relationShip.getToNode().getLabel());
                toNodeCql.append(":" + relationShip.getToNode().getName());
                property = relationShip.getToNode().getProperty();
                if (property != null && property.size() > 0) {
                    toNodeCql.append(Neo4jUtil.mapToProperty(property));
                }
            }
            toNodeCql.append(")");

            // 组装关系信息
            StringBuilder ship = new StringBuilder();
            ship.append("[");
            ship.append(relationShip.getLabel() + ":" + relationShip.getName());
            property = relationShip.getProperty();
            if(property != null && property.size() > 0) {
                ship.append(Neo4jUtil.mapToProperty(property));
            }
            ship.append("]");

            String matchCal = "";
            if(fromNode.size() > 0 || toNode.size() > 0) {
                matchCal = "MATCH ";
                if(fromNode.size() > 0) {
                    matchCal += "(" + relationShip.getFromNode().getName() + ":" +relationShip.getFromNode().getLabel() + ") ";
                }
                if(fromNode.size() > 0 && toNode.size() > 0) {
                    matchCal += ",";
                }
                if(toNode.size() > 0) {
                    matchCal += "(" + relationShip.getToNode().getName() + ":" +relationShip.getToNode().getLabel() + ") ";
                }
            }

            String cql = matchCal + "CREATE " + fromNodeCql.toString() +" - " + ship.toString() + " -> " + toNodeCql.toString();
            logger.info("createRelationShip ---> cql = " + cql);
        }
    }

    @Override
    public void deleteRelationShip(RelationShip relationShip) {
        if(relationShip.getFromNode() != null && relationShip.getToNode() != null) {
            String fromNode = "(" + relationShip.getFromNode().getName() +" : " +  relationShip.getFromNode().getLabel() + ")";
            String toNode = "(" + relationShip.getToNode().getName() +" : " +  relationShip.getToNode().getLabel() + ")";
            String ship = "[" + relationShip.getName() + "]";
            String cql = "MATCH " + fromNode + " - " + ship + " - " + toNode +" RETURN " + relationShip.getName();
            logger.info("deleteRelationShip ---> cql = " + cql);
        }
    }

    @Override
    public List<RelationShip> listRelationShipByLabel(String shipLabel) {
        List<RelationShip> shipList = new ArrayList<>();

        String formNodeLabel = "formNode", toNodeLabel = "toNode";
        String formNode = "(" + formNodeLabel + ")";
        String toNode = "(" + toNodeLabel + ")";
        String ship = "[node:`"+ shipLabel + "`]";
        String cql = "MATCH " + formNode +" - " + ship + " -> " + toNode + " RETURN " + formNodeLabel + " ," +toNodeLabel;
        logger.info("listRelationShipByLabel ---> cql = " + cql);

        return shipList;
    }


}
