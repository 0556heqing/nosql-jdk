package com.heqing.nosql.neo4j;

import com.alibaba.fastjson.JSON;
import com.heqing.nosql.neo4j.manager.Neo4jManager;
import com.heqing.nosql.neo4j.service.NodeService;
import com.heqing.nosql.neo4j.service.RelationService;
import com.heqing.nosql.neo4j.service.impl.NodeServiceImpl;
import com.heqing.nosql.neo4j.service.impl.RelationServiceImpl;
import org.neo4j.driver.v1.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void closeNeo4jDriver() {
        Neo4jManager.closeNeo4jDriver();
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
}
