package com.heqing.nosql.neo4j;

import java.util.List;
import java.util.Map;

/**
 * @author heqing
 * @date 2018/9/3 11:31
 */
public class NodeLanguageUtil {

    /**
     * 创建语句
     * @param nodeName 节点名
     * @param labelName 标签名
     * @param propertyMap 属性
     * @return 拼装后的创建语句
     */
    public static String getCreateCql(String nodeName, String labelName, Map<String, Object> propertyMap) {
        // 创建语句
        return "CREATE (" + nodeName + labelName +  Neo4jUtil.mapToProperty(propertyMap) + ") ";
    }

    /**
     * 查询语句
     * @param nodeName 节点名
     * @param labelName 标签名
     * @return 拼装后的查询语句
     */
    public static String getMatchCql(String nodeName, String labelName) {
        return "MATCH " + nodeName + ":" + labelName + ") ";
    }

    /**
     * 合并语句，相当于Create + Match。有修改，没有创建
     * @param nodeName 节点名
     * @param labelName 标签名
     * @return 拼装后的查询语句
     */
    public static String getMergeCql(String nodeName, String labelName, Map<String, Object> propertyMap) {
        // 合并语句
        return "MERGE (" + nodeName + labelName + Neo4jUtil.mapToProperty(propertyMap) + ") ";
    }

    /**
     * 设值语句
     * @param nodeName 节点名
     * @param propertyMap 属性
     * @return 拼装后的创建语句
     */
    public static String getSetCql(String nodeName, Map<String, Object> propertyMap) {
        StringBuilder property = new StringBuilder();
        if(propertyMap != null && propertyMap.size() > 0) {
            for (Map.Entry<String, Object> value : propertyMap.entrySet()) {
                property.append(nodeName + "." + value.getKey() + "=");
                property.append(Neo4jUtil.getProperty(value.getValue()) + ",");
            }
        }
        String result = property.toString();
        if(result.endsWith(",")) {
            result = result.replace(",", "");
        }
        // 设值语句
        return "SET  " + result + " ";
    }

    /**
     * 返回语句
     * @param nodeName 节点名
     * @param returnMap 需要返回的属性名 key：属性名 value:别名
     * @return 拼装后的查询语句
     */
    public static String getReturnCql(String nodeName, Map<String, String> returnMap) {
        String result = "";
        if(returnMap != null && returnMap.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> value : returnMap.entrySet()) {
                sb.append(nodeName + "." + value.getKey());
                if(value.getValue() != null && !"".equals(value.getValue())) {
                    sb.append(" as " + value.getValue());
                }
                sb.append(" ,");
            }
            result = sb.toString();
            if (result.endsWith(" ,")) {
                result = result.substring(0, result.length() - 1);
            }
        } else {
            result += nodeName;
        }

        return "RETURN " + result + " ";
    }

    /**
     * 删除节点语句
     * @param nodeName 节点名
     * @return 拼装后的删除节点语句
     */
    public static String getDeleteCql(String nodeName) {
        // 删除语句
        return "DELETE " + nodeName + " ";
    }

    /**
     * 删除节点属性语句
     * @param nodeName 节点名
     * @return 拼装后的删除节点属性语句
     */
    public static String getRemoveCql(String nodeName, List<String> propertys) {
        String remove = "";
        if(propertys != null && propertys.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String property : propertys) {
                sb.append(nodeName + "." + property + " ,");
            }
            remove = sb.toString();
            if (remove.endsWith(" ,")) {
                remove = remove.substring(0, remove.length() - 1);
            }
            remove = "REMOVE " + remove;
        }
        return remove + " ";
    }

    /**
     * 查询语句
     * @param whereCql 查询
     * @return 拼装后的查询语句
     */
    public static String getWhereCql(String whereCql) {
        return "WHERE " + whereCql;
    }

    /**
     * 排序语句
     * @param nodeName 节点名
     * @param sortCql key:参与排序的字段 value:大于等于0正序，否则倒序
     * @return 拼装后的排序语句
     */
    public static String getSortCql(String nodeName, Map<String, Integer> sortCql) {
        StringBuilder property = new StringBuilder();
        if(sortCql != null && sortCql.size() > 0) {
            for (Map.Entry<String, Integer> value : sortCql.entrySet()) {
                property.append(nodeName + "." + value.getKey());
                // 倒序
                if(value.getValue() < 0) {
                    property.append(" DESC");
                }
                property.append(",");
            }
        }
        String result = property.toString();
        if(result.endsWith(",")) {
            result = result.replace(",", "");
        }
        // 排序语句
        return "ORDER BY " + result+ " ";
    }

    /**
     * 分页语句
     * @param pageIndex 第几页
     * @param pageSize 每页数量
     * @return 拼装后的分页语句
     */
    public static String getPageCql(int pageIndex, int pageSize) {
        String skip = "", limit = "";
        if(pageIndex != 0) {
            int num = pageIndex;
            if(pageSize != 0) {
                num = pageIndex * pageSize;
            }
            skip = "SKIP " + num;
        }
        if(pageSize != 0) {
            limit = "LIMIT " + pageSize;
        }
        return skip + " " + limit + " ";
    }
}
