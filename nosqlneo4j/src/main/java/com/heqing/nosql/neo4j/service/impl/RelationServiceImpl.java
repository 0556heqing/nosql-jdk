package com.heqing.nosql.neo4j.service.impl;

import com.heqing.nosql.neo4j.LanguageUtil;
import com.heqing.nosql.neo4j.model.Relation;
import com.heqing.nosql.neo4j.service.RelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 关系
 * @author heqing
 * @date 2018/9/2 16:12
 */
public class RelationServiceImpl implements RelationService {

    private static final Logger logger = LoggerFactory.getLogger(RelationServiceImpl.class);

    @Override
    public void createRelation(Relation relation) {
        String cql = LanguageUtil.getRelationMergeCql(relation);
        logger.info("createRelationShip ---> cql = " + cql);
    }

    @Override
    public void updateRelation(Relation relation, String param) {
        String matchCql = LanguageUtil.getRelationMatchCql(relation);
        String whereCql = LanguageUtil.getWhereCql(param);
        String setCql = LanguageUtil.getRelationSetPropertyCql(relation);
        String returnCql = LanguageUtil.getRelationReturnCql(relation, null, null, null);
        String cql = matchCql + whereCql + setCql + returnCql;
        logger.info("addNodeLabel ---> cql = " + cql);
    }

    @Override
    public List<Relation> listRelation(Relation relation, String param, int pageNo, int pageSize) {

        String matchCql = LanguageUtil.getRelationMatchCql(relation);
        String whereCql = LanguageUtil.getWhereCql(param);
        String pageCql = LanguageUtil.getPageCql(pageNo, pageSize);
        String returnCql = LanguageUtil.getRelationReturnCql(relation, null, null, null);

        String cql = matchCql + whereCql + returnCql + pageCql;
        logger.info("listNode ---> cql = " + cql);
        return null;
    }

    @Override
    public void deleteRelation(Relation relation, String param) {
        String matchCql = LanguageUtil.getRelationMatchCql(relation);
        String whereCql = LanguageUtil.getWhereCql(param);
        String deleteCql = LanguageUtil.getRelationDeleteCql(relation);
        String cql = matchCql + whereCql + deleteCql;
        logger.info("deleteNode ---> cql = " + cql);
    }

}
