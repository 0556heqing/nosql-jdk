package com.heqing.nosql.neo4j;

import com.heqing.nosql.neo4j.manager.Neo4jManager;
import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.model.Relation;
import com.heqing.nosql.neo4j.service.NodeService;
import com.heqing.nosql.neo4j.service.RelationService;
import com.heqing.nosql.neo4j.service.impl.NodeServiceImpl;
import com.heqing.nosql.neo4j.service.impl.RelationServiceImpl;
import org.neo4j.driver.internal.shaded.io.netty.util.internal.StringUtil;
import org.neo4j.driver.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author heqing
 * @date 2018/8/24 16:00
 */
public class Neo4jUtil {

    private static final Logger logger = LoggerFactory.getLogger(Neo4jUtil.class);

    private static NodeService nodeService;

    private static RelationService relationService;

    public static Driver getNeo4jDriver() {
        return Neo4jManager.getNeo4jDriver();
    }

    public static NodeService getNode() {
        if(nodeService == null) {
            nodeService = new NodeServiceImpl();
        }
        return nodeService;
    }

    public static RelationService getRelationShip() {
        if(relationService == null) {
            relationService = new RelationServiceImpl();
        }
        return relationService;
    }

    /**
     * 将java对象转换为Map格式
     * @param obj java对象
     * @return Map格式
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<>(16);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
                map.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 执行Cypher语言并返回结果
     * @param cql 语句
     * @return 结果
     */
    public static StatementResult runCql(String cql) {
        Driver driver = getNeo4jDriver();
        Session session = driver.session();
        Transaction tran = session.beginTransaction();
        StatementResult result = tran.run(cql);
        tran.success();
        tran.close();
        session.close();
        driver.close();
        return result;
    }

    /**
     * 将Iterator结构改为List
     * @param iter Iterator结构数据
     * @return list结构数据
     */
    public static <T> List<T> IteratorToList(Iterator<T> iter) {
        List<T> list = new ArrayList<T>();
        while (iter.hasNext()) {
            list.add(iter.next());
        }
        return list;
    }

    /**
     * 执行Cypher语言的结果
     * @param statementResult 执行cql后的结果
     * @param node 节点
     * @param returnMap 需要返回的属性。为null将返回全部。否则key:属性名，value:别名
     * @return 结果
     */
    public static List<Node> listNodeReture(StatementResult statementResult, Node node, Map<String, String> returnMap) {
        List<Node> result = new ArrayList<>();
        while (statementResult.hasNext()) {
            Record record = statementResult.next();
            Node n = new Node();
            n.setName(node.getName());
            if(returnMap == null) {
                n.setId(record.get(0).asNode().id());
                n.setLabels(Neo4jUtil.IteratorToList(record.get(0).asNode().labels().iterator()));
                n.setProperty(record.get(0).asNode().asMap());
            } else {
                n.setLabels(node.getLabels());
                Map<String, Object> property = new HashMap<>(16);
                for (Map.Entry<String, String> value : returnMap.entrySet()) {
                    String key = StringUtil.isNullOrEmpty(value.getValue()) ? value.getKey() : value.getValue();
                    property.put(key, record.get(key).asObject());
                }
                n.setProperty(property);
            }
            result.add(n);
        }
        return result;
    }

    /**
     * 执行Cypher语言的结果
     * @param statementResult 执行cql后的结果
     * @param relation 关系
     * @param relationReturnMap 关系需要返回的属性。为null将返回全部。否则key:属性名，value:别名
     * @param fromNodeReturnMap 来源节点需要返回的属性。为null将返回全部。否则key:属性名，value:别名
     * @param tromNodeReturnMap 指向接待需要返回的属性。为null将返回全部。否则key:属性名，value:别名
     * @return 结果
     */
    public static List<Relation> listRelationReture(StatementResult statementResult, Relation relation, Map<String, String> relationReturnMap,
                                                    Map<String, String> fromNodeReturnMap, Map<String, String> tromNodeReturnMap) {
        List<String> labels = new ArrayList<>();
        labels.add(relation.getLabel());

        List<Relation> relationList = new ArrayList<>();
        while (statementResult.hasNext()) {
            Record record = statementResult.next();

            Node fromNode = new Node();
            fromNode.setName(relation.getFromNode().getName());
            if(fromNodeReturnMap == null) {
                fromNode.setId(record.get(0).asNode().id());
                fromNode.setLabels(Neo4jUtil.IteratorToList(record.get(0).asNode().labels().iterator()));
                fromNode.setProperty(record.get(0).asNode().asMap());
            } else {
                fromNode.setLabels(labels);
                Map<String, Object> property = new HashMap<>(16);
                for (Map.Entry<String, String> value : fromNodeReturnMap.entrySet()) {
                    String key = StringUtil.isNullOrEmpty(value.getValue()) ? value.getKey() : value.getValue();
                    property.put(key, record.get(0).asNode().get(key).asObject());
                }
                fromNode.setProperty(property);
            }

            Node toNode = new Node();
            toNode.setName(relation.getFromNode().getName());
            if(tromNodeReturnMap == null) {
                toNode.setId(record.get(1).asNode().id());
                toNode.setLabels(Neo4jUtil.IteratorToList(record.get(1).asNode().labels().iterator()));
                toNode.setProperty(record.get(1).asNode().asMap());
            } else {
                toNode.setLabels(labels);
                Map<String, Object> property = new HashMap<>(16);
                for (Map.Entry<String, String> value : tromNodeReturnMap.entrySet()) {
                    String key = StringUtil.isNullOrEmpty(value.getValue()) ? value.getKey() : value.getValue();
                    property.put(key, record.get(1).asNode().get(key).asObject());
                }
                toNode.setProperty(property);
            }

            Relation r = new Relation();
            toNode.setName(relation.getName());
            if(relationReturnMap == null) {
                r.setId(record.get(2).asRelationship().id());
                r.setLabel(relation.getLabel());
                r.setProperty(record.get(2).asRelationship().asMap());
            } else {
                r.setLabel(relation.getLabel());
                Map<String, Object> property = new HashMap<>(16);
                for (Map.Entry<String, String> value : relationReturnMap.entrySet()) {
                    String key = StringUtil.isNullOrEmpty(value.getValue()) ? value.getKey() : value.getValue();
                    property.put(key, record.get(2).asRelationship().get(key).asObject());
                }
                r.setProperty(property);
            }
            r.setFromNode(fromNode);
            r.setToNode(toNode);
            relationList.add(r);
        }
        return relationList;
    }
}
