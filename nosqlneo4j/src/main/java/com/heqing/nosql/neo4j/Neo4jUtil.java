package com.heqing.nosql.neo4j;

import com.alibaba.fastjson.JSON;
import com.heqing.nosql.neo4j.manager.Neo4jManager;
import com.heqing.nosql.neo4j.service.NodeService;
import com.heqing.nosql.neo4j.service.RelationShipService;
import com.heqing.nosql.neo4j.service.impl.NodeServiceImpl;
import com.heqing.nosql.neo4j.service.impl.RelationShipServiceImpl;
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

    private static RelationShipService relationShipService;

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

    public static RelationShipService getRelationShip() {
        if(relationShipService == null) {
            relationShipService = new RelationShipServiceImpl();
        }
        return relationShipService;
    }

    /**
     * 将标签集合改为符合cql的集合格式
     * @param labels 标签集合
     * @return 符合cql的标签格式
     */
    public static String listToLabel(List<String> labels) {
        StringBuilder label = new StringBuilder();
        if(labels != null && labels.size() > 0) {
            for (String labelName : labels) {
                label.append(":" + labelName);
            }
        }
        return label.toString();
    }

    /**
     * 将属性集合改为符合cql的集合格式
     * @param propertyMap 属性集合
     * @return 符合cql的属性格式
     */
    public static String mapToProperty(Map<String, Object> propertyMap) {
        StringBuilder property = new StringBuilder();
        if(propertyMap != null && propertyMap.size() > 0) {
            property.append("{");
            for (Map.Entry<String, Object> value : propertyMap.entrySet()) {
                property.append(value.getKey() + ":");
                property.append(Neo4jUtil.getProperty(value.getValue()) + ",");
            }
            property.append("}");
        }
        String result = property.toString();
        if(result.endsWith(",}")) {
            result = result.replace(",}", "}");
        }
        return result;
    }

    public static String getProperty(Object value) {
        String property = "";
        if (value instanceof Byte || value instanceof Character || value instanceof String) {
            // String类型，加入双引号“”
            property = "\"" + value.toString() + "\"";
        } else if (value instanceof Short || value instanceof Integer || value instanceof Long
                || value instanceof Float || value instanceof Double) {
            // 数字类型
            property = value.toString();
        } else if (value instanceof Boolean) {
            // boolean 类型
            if (((Boolean) value).booleanValue()) {
                property = "\"true\"";
            } else {
                property = "\"false\"";
            }
        } else if (value instanceof Date) {
            // Date类型，改为yyyy-MM-dd HH:mm:ss格式
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            property = "\"" + formatter.format(value) + "\"";
        } else {
            property = "\"" + JSON.toJSONString(value).replaceAll("\"", "\\\\\"") + "\"";
        }
        return property;
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
