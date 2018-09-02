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
     * @param propertys 属性集合
     * @param type 1:创建 2：修改
     * @return 符合cql的属性格式
     */
    public static String mapToProperty(Map<String, Object> propertys, int type) {
        StringBuilder property = new StringBuilder();
        if(propertys != null && propertys.size() > 0) {
            if(type == 1) {
                property.append("{");
            }
            for (Map.Entry<String, Object> value : propertys.entrySet()) {
                if(type == 1) {
                    property.append(value.getKey() + ":");
                } else if(type == 2) {
                    property.append(value.getKey() + "=");
                }

                if (value.getValue() instanceof Byte || value.getValue() instanceof Character || value.getValue() instanceof String) {
                    // String类型，加入双引号“”
                    property.append("\"" + value.getValue().toString() + "\"");
                } else if (value.getValue() instanceof Short
                        || value.getValue() instanceof Integer || value.getValue() instanceof Long
                        || value.getValue() instanceof Float || value.getValue() instanceof Double) {
                    // 数字类型
                    property.append(value.getValue().toString());
                } else if (value.getValue() instanceof Boolean) {
                    // boolean 类型
                    if (((Boolean) value.getValue()).booleanValue()) {
                        property.append("\"true\"");
                    } else {
                        property.append("\"false\"");
                    }
                } else if (value.getValue() instanceof Date) {
                    // Date类型，改为yyyy-MM-dd HH:mm:ss格式
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    property.append("\"" + formatter.format(value.getValue()) + "\"");
                } else {
                    property.append("\"" + JSON.toJSONString(value.getValue()).replaceAll("\"", "\\\\\"") + "\"");
                }
                property.append(",");
            }
            if(type == 1) {
                property.append("}");
            }
        }
        String result = property.toString();
        if(result.endsWith(",}")) {
            result = result.replace(",}", "}");
        }
        return result;
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
        Map<String, Object> map = new HashMap<>();
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
