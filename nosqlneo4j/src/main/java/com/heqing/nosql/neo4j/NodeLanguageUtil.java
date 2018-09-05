package com.heqing.nosql.neo4j;

import java.util.List;
import java.util.Map;

/**
 * @author heqing
 * @date 2018/9/3 11:31
 * 语句顺序:
 *      CREATE / MATCH / MERGE
 *      WHERE
 *      REMOVE / SET
 *      RETURN / DELETE
 *      ORDER BY
 *      LIMIT
 *      UNION  / UNION ALL
 */
public class NodeLanguageUtil {

    private static final String COMMA = ",";
    private static final String COLON = ":";

    /**
     * 创建语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_create_node.html">neo4j -> CREATE命令</a>
     * @param nodeName 节点名
     * @param labelName 标签名
     * @param propertyMap 属性
     * @return 拼装后的创建语句
     */
    public static String getCreateCql(String nodeName, String labelName, Map<String, Object> propertyMap) {
        // 创建语句
        return " CREATE (" + nodeName + ":" + labelName + Neo4jUtil.mapToProperty(propertyMap) + ") ";
    }

    private static String getLabel(List<String> labelList) {
        String labelCql = "";
        if(labelList != null) {
            labelCql += ":";
            for (String label : labelList) {
                labelCql += label + ":";
            }
            if (labelCql.endsWith(COLON)) {
                labelCql = labelCql.substring(0, labelCql.length() - 1);
            }
        }
        return labelCql;
    }

    /**
     * 查询语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_match_command.html">neo4j -> MATCH命令</a>
     * @param nodeName 节点名
     * @param labelList 标签名
     * @return 拼装后的查询语句
     */
    public static String getMatchCql(String nodeName, List<String> labelList) {
        return " MATCH (" + nodeName + getLabel(labelList) + ") ";
    }

    /**
     * 合并语句，相当于Create + Match。有修改，没有创建
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_merge.html">neo4j -> MERGE命令</a>
     * @param nodeName 节点名
     * @param labelList 标签名
     * @return 拼装后的查询语句
     */
    public static String getMergeCql(String nodeName, List<String> labelList, Map<String, Object> propertyMap) {
        // 合并语句
        return " MERGE (" + nodeName + getLabel(labelList) + Neo4jUtil.mapToProperty(propertyMap) + ") ";
    }

    /**
     * 查询语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_where_clause.html">neo4j -> WHERE子句</a>
     * @param whereCql 查询
     * @return 拼装后的查询语句
     */
    public static String getWhereCql(String whereCql) {
        String cql = "";
        if(!"".equals(whereCql)) {
            cql = " WHERE " + whereCql;
        }
        return cql;
    }

    /**
     * 删除节点属性语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_remove.html">neo4j -> 删除标签/属性</a>
     * @param nodeName 节点名
     * @return 拼装后的删除节点属性语句
     */
    public static String getRemovePropertyCql(String nodeName, List<String> propertys) {
        String cql = "";
        if(propertys != null && propertys.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String property : propertys) {
                sb.append(nodeName + "." + property + ",");
            }
            cql = sb.toString();
            if (cql.endsWith(COMMA)) {
                cql = cql.substring(0, cql.length() - 1);
            }
            cql = " REMOVE " + cql + " ";
        }
        return cql;
    }

    /**
     * 删除节点标签语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_remove.html">neo4j -> 删除标签/属性</a>
     * @param nodeName 节点名
     * @return 拼装后的删除节点属性语句
     */
    public static String getRemoveLabelCql(String nodeName, List<String> propertys) {
        String cql = "";
        if(propertys != null && propertys.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String property : propertys) {
                sb.append(nodeName + ":" + property + ",");
            }
            cql = sb.toString();
            if (cql.endsWith(COMMA)) {
                cql = cql.substring(0, cql.length() - 1);
            }
            cql = " REMOVE " + cql + " ";
        }
        return cql;
    }

    /**
     * 设值语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_set.html">neo4j -> 设值子句</a>
     * @param nodeName 节点名
     * @param propertyMap 属性
     * @return 拼装后的创建语句
     */
    public static String getSetPropertyCql(String nodeName, Map<String, Object> propertyMap) {
        String cql = "";
        if(propertyMap != null && propertyMap.size() > 0) {
            StringBuilder property = new StringBuilder();
            for (Map.Entry<String, Object> value : propertyMap.entrySet()) {
                property.append(nodeName + "." + value.getKey() + "=");
                property.append(Neo4jUtil.getProperty(value.getValue()));
                property.append(",");
            }
            cql = property.toString();
            if(cql.endsWith(COMMA)) {
                cql = cql.substring(0, cql.length() - 1);
            }
            cql = " SET " + cql + " ";
        }
        // 设值语句
        return cql;
    }

    /**
     * 设值标签语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_set.html">neo4j -> 设值子句</a>
     * @param nodeName 节点名
     * @param labelList 标签
     * @return 拼装后的创建语句
     */
    public static String getSetLabelCql(String nodeName, List<String> labelList) {
        String cql = "";
        if(labelList != null && labelList.size() > 0) {
            StringBuilder property = new StringBuilder();
            for (String label : labelList) {
                property.append(nodeName + ":" + label);
                property.append(",");
            }
            cql = property.toString();
            if(cql.endsWith(COMMA)) {
                cql = cql.substring(0, cql.length() - 1);
            }
            cql = " SET " + cql + " ";
        }
        // 设值语句
        return cql;
    }

    /**
     * 返回语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_return_clause.html">neo4j -> return子句</a>
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
                sb.append(",");
            }
            result = sb.toString();
            if (result.endsWith(COMMA)) {
                result = result.substring(0, result.length() - 1);
            }
        } else {
            result += nodeName;
        }

        return " RETURN " + result + " ";
    }

    /**
     * 删除节点语句
     * @see <a href="https://www.w3cschool.cn/neo4j/delete.html">neo4j -> 删除子句</a>
     * @param nodeName 节点名
     * @return 拼装后的删除节点语句
     */
    public static String getDeleteCql(String nodeName) {
        String cql = "";
        if(!"".equals(nodeName)) {
            cql = " DELETE " + nodeName + " ";
        }
        // 删除语句
        return cql;
    }

    /**
     * 排序语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_sorting.html">neo4j -> 排序</a>
     * @param nodeName 节点名
     * @param sortCql key:参与排序的字段 value:大于等于0正序，否则倒序
     * @return 拼装后的排序语句
     */
    public static String getSortCql(String nodeName, Map<String, Integer> sortCql) {
        String cql = "";
        StringBuilder property = new StringBuilder();
        if(sortCql != null && sortCql.size() > 0) {
            for (Map.Entry<String, Integer> value : sortCql.entrySet()) {
                property.append(nodeName + "." + value.getKey());
                // 倒序
                if(value.getValue() < 0) {
                    property.append(" DESC");
                }
                property.append(", ");
            }

            String result = property.toString();
            if(result.endsWith(COMMA)) {
                result = result.substring(0, result.length() - 1);
            }
            cql = " ORDER BY " + result+ " ";
        }
        // 排序语句
        return cql;
    }

    /**
     * 分页语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_limit_skip.html">neo4j -> 分页</a>
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
            skip = " SKIP " + num;
        }
        if(pageSize != 0) {
            limit = " LIMIT " + pageSize;
        }
        return skip + limit;
    }
}
