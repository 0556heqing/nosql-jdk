package com.heqing.nosql.neo4j.service;

import com.heqing.nosql.neo4j.model.Node;

import java.util.List;
import java.util.Map;

/**
 * 节点操作接口
 * @author heqing
 * @date 2018/8/30 15:32
 */
public interface NodeService {

    /**
     * 修改节点属性
     * @param node 节点
     * @param param 筛选条件
     * @param labelList 需要增加的标签
     */
    void addNodeLabel(Node node, String param, List<String> labelList);

    /**
     * 创建节点,若存在则修改
     * @param node 节点
     */
    void createNode(Node node);

    /**
     * 删除所有节点
     * @param node 节点
     * @param param 筛选条件
     */
    void deleteNode(Node node, String param);

    /**
     * 删除节点某个标签
     * @param node 节点
     * @param param 筛选条件
     * @param labelList 需要删除的标签
     * @return 节点
     */
    Node deleteNodeLabel(Node node, String param, List<String> labelList);

    /**
     * 删除节点某个属性
     * @param node 节点
     * @param param 筛选条件
     * @param propertyList 需要删除的属性
     * @return 节点
     */
    Node deleteNodeProperty(Node node, String param, List<String> propertyList);

    /**
     * 根据条件获取节点
     * @param nodeName 节点名
     * @param labelList 标签名
     * @param param 筛选条件，为null不添加筛选条件
     * @param sort 排序条件，为null不添加排序。 key:属性名，value:大于等于0正序，小于0倒序
     * @param pageNo 第几页，为0不跳过数据
     * @param pageSize 每页数量，为0不截取数据
     * @param returnMap 需要返回的属性。为null将返回全部。否则key:属性名，value:别名
     * @return 符合条件的节点
     */
    List<Node> listNode(String nodeName, List<String> labelList, String param, Map<String, Integer> sort, int pageNo, int pageSize, Map<String, String> returnMap);

    /**
     * 修改节点属性
     * @param node 节点
     * @param param 筛选条件
     */
    void updateNodeProperty(Node node, String param);

}
