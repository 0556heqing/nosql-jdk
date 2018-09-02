package com.heqing.nosql.neo4j.service;

import com.heqing.nosql.neo4j.model.Node;

import java.util.List;

/**
 * @author heqing
 * @date 2018/8/30 15:32
 */
public interface NodeService {

    /**
     * 创建节点
     * @param node 节点
     */
    void createNode(Node node);

    /**
     * 为节点创建多个标签
     * @param nodeName 节点名称
     * @param labels 多个标签
     */
    void createLabelByNodeName(String nodeName, List<String> labels);

    /**
     * 删除节点
     * @param node 节点
     */
    void deleteNode(Node node);

    /**
     * 删除节点某个属性
     * @param node 节点
     * @param propertyNameList 需要删除的属性
     * @retun 节点
     */
    Node removeNodeProperty(Node node, List<String> propertyNameList);

    /**
     * 删除节点某个标签
     * @param node 节点
     * @param labelNameList 需要删除的标签
     * @retun 节点
     */
    Node removeNodeLabel(Node node, List<String> labelNameList);

    /**
     * 修改节点
     * @param node 节点
     */
    Node updateNode(Node node);

    /**
     * 获取标签下节点的属性
     * @param labelName 标签名
     * @param propertys 返回的属性
     * @return 节点
     */
    List<Node> listNodeByLabel(String labelName, List<String> propertys);
}
