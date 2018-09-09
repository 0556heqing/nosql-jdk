package com.heqing.nosql.neo4j.service;

import com.heqing.nosql.neo4j.model.Relation;

import java.util.List;

/**
 * @author heqing
 * @date 2018/9/2 16:11
 */
public interface RelationService {

    /**
     * 创建关系
     * @param relation 关系
     * @return 关系信息
     */
    Relation createRelation(Relation relation);

    /**
     * 修改节点
     * @param relation 关系
     * @param param 筛选条件
     * @return 关系信息
     */
    Relation updateRelation(Relation relation, String param);

    /**
     * 根据条件获取关系
     * @param relation 关系
     * @param param 筛选条件，为null不添加筛选条件
//     * @param sort 排序条件，为null不添加排序。 key:属性名，value:大于等于0正序，小于0倒序
     * @param pageNo 第几页，为0不跳过数据
     * @param pageSize 每页数量，为0不截取数据
     * @return 符合条件的关系
     */
    List<Relation> listRelation(Relation relation, String param, int pageNo, int pageSize);


    /**
     * 删除关系
     * @param relation 关系
     * @param param 筛选条件，为null不添加筛选条件
     */
    void deleteRelation(Relation relation, String param);

}
