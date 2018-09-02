package com.heqing.nosql.neo4j.service.impl;

import com.heqing.nosql.neo4j.Neo4jUtil;
import com.heqing.nosql.neo4j.service.CreateService1;
import org.neo4j.driver.v1.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author heqing
 * @date 2018/8/27 17:35
 */
public class CreateService1Impl implements CreateService1 {

    private static final Logger logger = LoggerFactory.getLogger(CreateService1Impl.class);

    @Override
    public void createNoPropertyNode(String nodeName, String labelName) {
        List<String> labelNameList = new ArrayList<>();
        labelNameList.add(labelName);
        createNoPropertyNode(nodeName, labelNameList);
    }

    @Override
    public void createNoPropertyNode(String nodeName, List<String> labelNameList) {
        Session session = Neo4jUtil.getNeo4jDriver().session();
        String label = "";
        for(String labelName : labelNameList) {
            label += ":" + labelName;
        }
        String cql = "CREATE (" + nodeName + label + ")";
        logger.info("---> cql = " + cql);
        session.run(cql);
        session.close();
        Neo4jUtil.closeNeo4jDriver();
    }

    @Override
    public void createNodeByMap(String nodeName, String labelName, Map<String, Object> value) {
        List<Map<String, Object>> valueList = new ArrayList<>();
        valueList.add(value);
        createNode(nodeName, labelName, valueList);
    }

    @Override
    public void createNodeByMap(String nodeName, List<String> labelNameList, Map<String, Object> value) {
        List<Map<String, Object>> valueList = new ArrayList<>();
        valueList.add(value);
        createNode(nodeName, labelNameList, valueList);
    }

    @Override
    public void createNodeByObject(String nodeName, String labelName, Object obj) {
        createNodeByMap(nodeName, labelName, Neo4jUtil.objectToMap(obj));
    }

    @Override
    public void createNodeByObject(String nodeName, List<String> labelNameList, Object obj) {
        createNodeByMap(nodeName, labelNameList, Neo4jUtil.objectToMap(obj));
    }

    @Override
    public void createNode(String nodeName, String labelName, List<Map<String, Object>> valueList) {
        if(valueList == null || valueList.size() < 1) {
            createNoPropertyNode(nodeName, labelName);
            return;
        }
        List<String> labelNameList = new ArrayList<>();
        labelNameList.add(labelName);
        createNode(nodeName, labelNameList, valueList);
    }

    @Override
    public void createNode(String nodeName, List<String> labelNameList, List<Map<String, Object>> valueList) {
        if(valueList == null || valueList.size() < 1) {
            createNoPropertyNode(nodeName, labelNameList);
            return;
        }
        Session session = Neo4jUtil.getNeo4jDriver().session();
        String label = "";
        for(String labelName : labelNameList) {
            label += ":" + labelName;
        }
        for(Map<String, Object> value : valueList) {
            String cql = "CREATE (" + nodeName + label + "{" + Neo4jUtil.mapToProperty(value, 1) + "})";
            logger.info("---> cql = " + cql);
            session.run(cql);
        }
        session.close();
        Neo4jUtil.closeNeo4jDriver();
    }
}
