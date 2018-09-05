package com.heqing.nosql.neo4j.service.impl;

import com.heqing.nosql.neo4j.NodeLanguageUtil;
import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public void addNodeLabel(Node node, String param, List<String> labelList) {
        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabels());
        String whereCal = NodeLanguageUtil.getWhereCql(param);
        String setCql = NodeLanguageUtil.getSetLabelCql(node.getName(), labelList);
        String cql = matchCql + whereCal + setCql;
        logger.info("addNodeLabel ---> cql = " + cql);
    }

    @Override
    public void createNode(Node node) {
        String cql = NodeLanguageUtil.getMergeCql(node.getName(), node.getLabels(), node.getProperty());
        logger.info("createNode ---> cql = " + cql);
    }

    @Override
    public void deleteNode(Node node, String param) {
        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabels());
        String whereCal = NodeLanguageUtil.getWhereCql(param);
        String deleteCql = NodeLanguageUtil.getDeleteCql(node.getName());
        String cql = matchCql + whereCal + deleteCql;
        logger.info("deleteNode ---> cql = " + cql);
    }

    @Override
    public Node deleteNodeLabel(Node node, String param, List<String> labelList) {
        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabels());
        String whereCal = NodeLanguageUtil.getWhereCql(param);
        String removeCql = NodeLanguageUtil.getRemoveLabelCql(node.getName(), labelList);
        String returnCal = NodeLanguageUtil.getReturnCql(node.getName(), null);
        String cql = matchCql + whereCal + removeCql + returnCal;
        logger.info("deleteNodeLabel ---> cql = " + cql);

        return null;
    }

    @Override
    public Node deleteNodeProperty(Node node, String param, List<String> propertyList) {
        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabels());
        String whereCal = NodeLanguageUtil.getWhereCql(param);
        String removeCql = NodeLanguageUtil.getRemovePropertyCql(node.getName(), propertyList);
        String returnCal = NodeLanguageUtil.getReturnCql(node.getName(), null);
        String cql = matchCql + whereCal + removeCql + returnCal;
        logger.info("deleteNodeProperty ---> cql = " + cql);

        return null;
    }

    @Override
    public List<Node> listNode(String nodeName, List<String> labelList, String param, Map<String, Integer> sort, int pageNo, int pageSize, Map<String, String> returnMap) {
        String matchCql = NodeLanguageUtil.getMatchCql(nodeName, labelList);
        String whereCql = NodeLanguageUtil.getWhereCql(param);
        String sortCql = NodeLanguageUtil.getSortCql(nodeName, sort);
        String pageCql = NodeLanguageUtil.getPageCql(pageNo, pageSize);
        String returnCql = NodeLanguageUtil.getReturnCql(nodeName, returnMap);

        String cql = matchCql + whereCql + returnCql + sortCql + pageCql;
        logger.info("listNode ---> cql = " + cql);
        return null;
    }

    @Override
    public void updateNodeProperty(Node node, String param) {
        String matchCql = NodeLanguageUtil.getMatchCql(node.getName(), node.getLabels());
        String whereCal = NodeLanguageUtil.getWhereCql(param);
        String setCql = NodeLanguageUtil.getSetPropertyCql(node.getName(), node.getProperty());
        String cql = matchCql + whereCal + setCql;
        logger.info("updateNode ---> cql = " + cql);
    }

}
