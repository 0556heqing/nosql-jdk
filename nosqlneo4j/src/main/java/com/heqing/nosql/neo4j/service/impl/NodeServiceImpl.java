package com.heqing.nosql.neo4j.service.impl;

import com.heqing.nosql.neo4j.Neo4jUtil;
import com.heqing.nosql.neo4j.NodeLanguageUtil;
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
        String cql = NodeLanguageUtil.getCreateCql(node.getName(), node.getLabel(), node.getProperty());
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
        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabel());
        String deleteCql = NodeLanguageUtil.getDeleteCql(node.getName());
        String cql = matchCql + deleteCql;
        logger.info("deleteNode ---> cql = " + cql);
    }

    @Override
    public Node removeNodeProperty(Node node, List<String> propertyNameList) {
        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabel());
        String removeCql = NodeLanguageUtil.getRemoveCql(node.getName(), propertyNameList);
        String returnCal = NodeLanguageUtil.getReturnCql(node.getName(), null);
        String cql = matchCql + removeCql + returnCal;
        logger.info("removeProperty ---> cql = " + cql);

        return null;
    }

    @Override
    public Node removeNodeLabel(Node node, List<String> propertyNameList) {
        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabel());
        String removeCql = NodeLanguageUtil.getRemoveCql(node.getName(), propertyNameList);
        String returnCal = NodeLanguageUtil.getReturnCql(node.getName(), null);
        String cql = matchCql + removeCql + returnCal;
        logger.info("removeProperty ---> cql = " + cql);

        return null;
    }

    @Override
    public Node updateNode(Node node) {
        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabel());
        String setCql = NodeLanguageUtil.getSetCql(node.getName(), node.getProperty());
        String returnCal = NodeLanguageUtil.getReturnCql(node.getName(), null);
        String cql = matchCql + setCql + returnCal;
        logger.info("updateNode ---> cql = " + cql);

        return null;
    }

    @Override
    public List<Node> listNodeByLabel(Node node, Map<String, String> propertyMap) {
        List<Node> nodeList = new ArrayList<>();

        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabel());
        String returnCal = NodeLanguageUtil.getReturnCql(node.getName(), propertyMap);
        String cql = matchCql + returnCal;
        logger.info("listNodeByLabel ---> cql = " + cql);

        return nodeList;
    }

}
