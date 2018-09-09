package com.heqing.nosql.neo4j.service.impl;

import com.heqing.nosql.neo4j.LanguageUtil;
import com.heqing.nosql.neo4j.Neo4jUtil;
import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.model.Relation;
import com.heqing.nosql.neo4j.service.RelationService;
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
 * 关系
 * @author heqing
 * @date 2018/9/2 16:12
 */
public class RelationServiceImpl implements RelationService {

    private static final Logger logger = LoggerFactory.getLogger(RelationServiceImpl.class);

    @Override
    public Relation createRelation(Relation relation) {
        Relation r = null;

        String matchCql = LanguageUtil.getRelationMergeCql(relation);
        String returnCql = LanguageUtil.getRelationReturnCql(relation, null, null, null);
        String cql = matchCql + returnCql;
        logger.info("createRelationShip ---> cql = " + cql);

        StatementResult statementResult = Neo4jUtil.runCql(cql);
        List<Relation> relationList = Neo4jUtil.listRelationReture(Neo4jUtil.runCql(cql), relation, null, null, null);
        if(relationList.size() > 0) {
            r = relationList.get(0);
        }
        return r;
    }

    @Override
    public Relation updateRelation(Relation relation, String param) {
        Relation r = null;

        String matchCql = LanguageUtil.getRelationMatchCql(relation);
        String whereCql = LanguageUtil.getWhereCql(param);
        String setCql = LanguageUtil.getRelationSetPropertyCql(relation);
        String returnCql = LanguageUtil.getRelationReturnCql(relation, null, null, null);
        String cql = matchCql + whereCql + setCql + returnCql;
        logger.info("updateRelation ---> cql = " + cql);

        List<Relation> relationList = Neo4jUtil.listRelationReture(Neo4jUtil.runCql(cql), relation, null, null, null);
        if(relationList.size() > 0) {
            r = relationList.get(0);
        }
        return r;
    }

    @Override
    public List<Relation> listRelation(Relation relation, String param, int pageNo, int pageSize) {
        String matchCql = LanguageUtil.getRelationMatchCql(relation);
        String whereCql = LanguageUtil.getWhereCql(param);
        String pageCql = LanguageUtil.getPageCql(pageNo, pageSize);
        String returnCql = LanguageUtil.getRelationReturnCql(relation, null, null, null);
        String cql = matchCql + whereCql + returnCql + pageCql;
        logger.info("listRelation ---> cql = " + cql);

        return Neo4jUtil.listRelationReture(Neo4jUtil.runCql(cql), relation, null, null, null);
    }

    @Override
    public void deleteRelation(Relation relation, String param) {
        String matchCql = LanguageUtil.getRelationMatchCql(relation);
        String whereCql = LanguageUtil.getWhereCql(param);
        String deleteCql = LanguageUtil.getRelationDeleteCql(relation);
        String cql = matchCql + whereCql + deleteCql;
        logger.info("deleteRelation ---> cql = " + cql);

        Neo4jUtil.runCql(cql);
    }

}
