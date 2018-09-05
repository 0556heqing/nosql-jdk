package com.heqing.nosql.neo4j.service;

import com.heqing.nosql.neo4j.model.RelationShip;

import java.util.List;

/**
 * @author heqing
 * @date 2018/9/2 16:11
 */
public interface RelationShipService {

    /**
     * 创建关系
     * @param relationShip 关系
     */
    void createRelationShip(RelationShip relationShip);

    /**
     * 修改节点
     * @param relationShip 关系
     */
    void updateRelationShip(RelationShip relationShip);

//    /**
//     * 删除关系
//     * @param relationShip 关系
//     */
//    void deleteRelationShip(RelationShip relationShip);
//
//    /**
//     * 获取标签下关系的属性
//     * @param shipLabel 关系标签
//     * @return 节点
//     */
//    List<RelationShip> listRelationShipByLabel(String shipLabel);
}
