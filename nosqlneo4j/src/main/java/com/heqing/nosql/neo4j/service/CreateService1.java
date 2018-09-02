package com.heqing.nosql.neo4j.service;

import java.util.List;
import java.util.Map;

/**
 * 创建节点/关系
 * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_create_node.html">创建节点</a>
 * @author heqing
 * @date 2018/8/27 16:08
 */
public interface CreateService1 {

    /**
     * 创建没有属性的节点
     *
     * @param nodeName 节点的名称
     * @param labelName 节点的标签名称
     */
    void createNoPropertyNode(String nodeName, String labelName);

    /**
     * 创建没有属性的节点
     *
     * @param nodeName 节点的名称
     * @param labelNameList 节点的标签名称
     */
    void createNoPropertyNode(String nodeName, List<String> labelNameList);

    /**
     * 使用属性创建节点
     *
     * @param nodeName 节点的名称
     * @param labelName 节点的标签名称
     * @param value 属性值
     */
    void createNodeByMap(String nodeName, String labelName, Map<String, Object> value);


    /**
     * 使用属性创建节点
     *
     * @param nodeName 节点的名称
     * @param labelNameList 节点的标签名称
     * @param value 属性值
     */
    void createNodeByMap(String nodeName, List<String> labelNameList, Map<String, Object> value);

    /**
     * 使用属性创建节点
     *
     * @param nodeName 节点的名称
     * @param labelName 节点的标签名称
     * @param obj 属性值
     */
    void createNodeByObject(String nodeName, String labelName, Object obj);


    /**
     * 使用属性创建节点
     *
     * @param nodeName 节点的名称
     * @param labelNameList 节点的标签名称
     * @param obj 属性值
     */
    void createNodeByObject(String nodeName, List<String> labelNameList, Object obj);

    /**
     * 使用属性创建节点
     *
     * @param nodeName 节点的名称
     * @param labelName 节点的标签名称
     * @param valueList 属性值
     */
    void createNode(String nodeName, String labelName, List<Map<String, Object>> valueList);


    /**
     * 使用属性创建节点
     *
     * @param nodeName 节点的名称
     * @param labelNameList 节点的标签名称
     * @param valueList 属性值
     */
    void createNode(String nodeName, List<String> labelNameList, List<Map<String, Object>> valueList);
}
