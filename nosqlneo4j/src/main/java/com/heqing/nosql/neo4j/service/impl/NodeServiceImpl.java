package com.heqing.nosql.neo4j.service.impl;

import com.heqing.nosql.neo4j.Neo4jUtil;
import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 节点
 * @author heqing
 * @date 2018/8/30 15:32
 */
public class NodeServiceImpl implements NodeService {

    private static final Logger logger = LoggerFactory.getLogger(NodeServiceImpl.class);

    @Override
    public void createNode(Node node) {
        String nodeName = node.getName();
        String labelName = node.getLabel()==null ? "" : ":"+node.getLabel();
        String property = Neo4jUtil.mapToProperty(node.getProperty(), 1);
        String cql = "CREATE (" + nodeName + labelName + property + ")";
        logger.info("createNode ---> cql = " + cql);
    }

    @Override
    public void createLabelByNodeName(String nodeName, List<String> labels) {
        if(labels.size() > 0) {
            String labelName = Neo4jUtil.listToLabel(labels);
            String cql = "CREATE (" + nodeName + labelName + ")";
            logger.info("createLabelByNodeName ---> cql = " + cql);
        }
    }

    @Override
    public void deleteNode(Node node) {
        String cql = "MATCH ("+node.getName()+" : " + node.getLabel() + ") DELETE "+node.getName();
        logger.info("deleteNode ---> cql = " + cql);
    }

    @Override
    public Node removeNodeProperty(Node node, List<String> propertyNameList) {
        String nodeName = node.getName();
        String labelName = node.getLabel()==null ? "" : ":"+node.getLabel();
        String property = Neo4jUtil.mapToProperty(node.getProperty(), 1);
        String matchCql = "MATCH  (" + nodeName + labelName + property + ") ";
        String removeCql = "REMOVE ";
        if(propertyNameList.size() > 0) {
            for(String propertyName : propertyNameList) {
                removeCql += nodeName + "." + propertyName + " ,";
            }
            if(removeCql.endsWith(" ,")) {
                removeCql = removeCql.substring(0, removeCql.length()-1);
            }
        } else {
            removeCql += nodeName;
        }
        String returnCal = " RETURN " + nodeName;
        String cql = matchCql + removeCql + returnCal;
        logger.info("removeProperty ---> cql = " + cql);

        return null;
    }

    @Override
    public Node removeNodeLabel(Node node, List<String> propertyNameList) {
        String nodeName = node.getName();
        String labelName = node.getLabel()==null ? "" : ":"+node.getLabel();
        String property = Neo4jUtil.mapToProperty(node.getProperty(), 1);
        String matchCql = "MATCH  (" + nodeName + labelName + property + ") ";
        String removeCql = "REMOVE ";
        if(propertyNameList.size() > 0) {
            for(String propertyName : propertyNameList) {
                removeCql += nodeName + ":" + propertyName + " ,";
            }
            if(removeCql.endsWith(" ,")) {
                removeCql = removeCql.substring(0, removeCql.length()-1);
            }
        } else {
            removeCql += nodeName;
        }
        String returnCal = " RETURN " + nodeName;
        String cql = matchCql + removeCql + returnCal;
        logger.info("removeProperty ---> cql = " + cql);

        return null;
    }

    @Override
    public Node updateNode(Node node) {
        String nodeName = node.getName();
        String labelName = node.getLabel()==null ? "" : ":"+node.getLabel();
        String matchCql = "MATCH  (" + nodeName + ":" + labelName + ") ";
        String setCql = "SET " + Neo4jUtil.mapToProperty(node.getProperty(), 2);
//        Map<String, Object> propertys = Neo4jUtil.mapToProperty(node.getProperty());
//        for (Map.Entry<String, Object> value : node.getProperty().entrySet()) {
//            setCql += node.getName() + "." + value.getKey() + "=" + value.getValue().toString() + " ,";
//        }
//        setCql = setCql.substring(0, setCql.length()-1);
        String returnCal = " RETURN " + nodeName;
        String cql = matchCql + setCql + returnCal;
        logger.info("updateNode ---> cql = " + cql);

        return null;
    }

    @Override
    public List<Node> listNodeByLabel(String labelName, List<String> propertys) {
        List<Node> nodeList = new ArrayList<>();

        String nodeName = "node";
        String matchCql = "MATCH ("+nodeName+":"+labelName+")";
        String returnCql = " RETURN ";
        if(propertys != null && propertys.size() > 0) {
            for (String property : propertys) {
                returnCql += nodeName + "." + property + " ,";
            }
            if (returnCql.endsWith(" ,")) {
                returnCql = returnCql.substring(0, returnCql.length() - 1);
            }
        } else {
            returnCql += nodeName;
        }
        logger.info("listNodeByName ---> cql = " + (matchCql + returnCql));

        return nodeList;
    }

}
