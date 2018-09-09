package com.heqing.nosql.neo4j.service.impl;

import com.heqing.nosql.neo4j.LanguageUtil;
import com.heqing.nosql.neo4j.Neo4jUtil;
import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.service.NodeService;
import org.neo4j.driver.internal.shaded.io.netty.util.internal.StringUtil;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
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
    public Node addNodeLabel(Node node, String param, List<String> labelList) {
        String matchCql = LanguageUtil.getNodeMatchCql(node);
        String whereCql = LanguageUtil.getWhereCql(param);
        node.setLabels(labelList);
        String setCql = LanguageUtil.getNodeSetLabelCql(node);
        String returnCql = LanguageUtil.getNodeReturnCql(node, null);
        String cql = matchCql + whereCql + setCql + returnCql;
        logger.info("addNodeLabel ---> cql = " + cql);

        Node n = null;
        List<Node> result = Neo4jUtil.listNodeReture(Neo4jUtil.runCql(cql), node, null);
        if(result.size() > 0) {
            n = result.get(0);
        }
        return n;
    }

    @Override
    public Node createNode(Node node) {
        String matchCql = LanguageUtil.getNodeMergeCql(node);
        String returnCql = LanguageUtil.getNodeReturnCql(node, null);
        String cql = matchCql + returnCql;
        logger.info("createNode ---> cql = " + cql);

        Node n = null;
        List<Node> result = Neo4jUtil.listNodeReture(Neo4jUtil.runCql(cql), node, null);
        if(result.size() > 0) {
            n = result.get(0);
        }
        return n;
    }

    @Override
    public void deleteNode(Node node, String param) {
        String matchCql = LanguageUtil.getNodeMatchCql(node);
        String whereCql = LanguageUtil.getWhereCql(param);
        String deleteCql = LanguageUtil.getNodeDeleteCql(node);
        String cql = matchCql + whereCql + deleteCql;
        logger.info("deleteNode ---> cql = " + cql);

        Neo4jUtil.runCql(cql);
    }

    @Override
    public Node deleteNodeLabel(Node node, String param, List<String> labelList) {
        String matchCql = LanguageUtil.getNodeMatchCql(node);
        String whereCql = LanguageUtil.getWhereCql(param);
        String removeCql = LanguageUtil.getNodeRemoveLabelCql(node, labelList);
        String returnCql = LanguageUtil.getNodeReturnCql(node, null);
        String cql = matchCql + whereCql + removeCql + returnCql;
        logger.info("deleteNodeLabel ---> cql = " + cql);

        Node n = null;
        List<Node> result = Neo4jUtil.listNodeReture(Neo4jUtil.runCql(cql), node, null);
        if(result.size() > 0) {
            n = result.get(0);
        }
        return n;
    }

    @Override
    public Node deleteNodeProperty(Node node, String param, List<String> propertyList) {

        String matchCql = LanguageUtil.getNodeMatchCql(node);
        String whereCal = LanguageUtil.getWhereCql(param);
        String removeCql = LanguageUtil.getNodeRemovePropertyCql(node, propertyList);
        String returnCql = LanguageUtil.getNodeReturnCql(node, null);
        String cql = matchCql + whereCal + removeCql + returnCql;
        logger.info("deleteNodeProperty ---> cql = " + cql);

        Node n = null;
        List<Node> result = Neo4jUtil.listNodeReture(Neo4jUtil.runCql(cql), node, null);
        if(result.size() > 0) {
            n = result.get(0);
        }
        return n;
    }

    @Override
    public List<Node> listNode(String nodeName, List<String> labelList, String param, Map<String, Integer> sort, int pageNo, int pageSize, Map<String, String> returnMap) {
        Node node = new Node();
        node.setName(nodeName);
        node.setLabels(labelList);

        String matchCql = LanguageUtil.getNodeMatchCql(node);
        String whereCql = LanguageUtil.getWhereCql(param);
        String sortCql = LanguageUtil.getNodeSortCql(node, sort);
        String pageCql = LanguageUtil.getPageCql(pageNo, pageSize);
        String returnCql = LanguageUtil.getNodeReturnCql(node, returnMap);
        String cql = matchCql + whereCql + returnCql + sortCql + pageCql;
        logger.info("listNode ---> cql = " + cql);

        return Neo4jUtil.listNodeReture(Neo4jUtil.runCql(cql), node, returnMap);
    }

    @Override
    public Node updateNodeProperty(Node node, String param) {
        String matchCql = LanguageUtil.getNodeMatchCql(node);
        String whereCql = LanguageUtil.getWhereCql(param);
        String setCql = LanguageUtil.getNodeSetPropertyCql(node);
        String returnCql = LanguageUtil.getNodeReturnCql(node, null);
        String cql = matchCql + whereCql + setCql + returnCql;
        logger.info("updateNodeProperty ---> cql = " + cql);

        Node n = null;
        List<Node> result = Neo4jUtil.listNodeReture(Neo4jUtil.runCql(cql), node, null);
        if(result.size() > 0) {
            n = result.get(0);
        }
        return n;
    }

}
