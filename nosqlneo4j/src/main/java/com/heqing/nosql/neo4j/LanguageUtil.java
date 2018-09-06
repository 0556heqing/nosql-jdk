package com.heqing.nosql.neo4j;

import com.alibaba.fastjson.JSON;
import com.heqing.nosql.neo4j.model.Node;
import com.heqing.nosql.neo4j.model.Relation;
import org.neo4j.driver.internal.shaded.io.netty.util.internal.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class LanguageUtil {

    public static final String COMMA = ",";
    public static final String COLON = ":";

    /**
     * 创建节点语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_create_node.html">neo4j -> CREATE命令</a>
     * @param node 节点
     * @return 拼装后的创建语句
     */
    public static String getNodeCreateCql(Node node) {
        StringBuilder cql = new StringBuilder();
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            cql.append(" CREATE (" + node.getName());
            if(node.getLabels() != null && node.getLabels().size() > 0) {
                cql.append(getLabel(node.getLabels()));
            }
            if(node.getProperty() != null && node.getProperty().size() > 0) {
                cql.append(mapToProperty(node.getProperty()));
            }
            cql.append(")");
        }
        return cql.toString();
    }

    /**
     * 创建关系语句
     * @param relation 关系
     * @return 拼装后的创建语句
     */    public static String getRelationCreateCql(Relation relation) {
        String cql = "";
        if(relation != null && relation.getFromNode() != null && relation.getToNode() != null) {
            // 来源节点
            Node formNode = relation.getFromNode();
            String formString = "(" + formNode.getName() + getLabel(formNode.getLabels()) + mapToProperty(formNode.getProperty()) + ")";
            // 关系
            String relationString = "[" + relation.getName() + getLabel(relation.getLabels()) + mapToProperty(relation.getProperty()) + "]";
            // 指向节点
            Node toNode = relation.getToNode();
            String toString = "(" + toNode.getName() + getLabel(toNode.getLabels()) + mapToProperty(toNode.getProperty()) + ")";

            cql = "CREATE " + formString + " - " + relationString + " -> "+ toString;
        }
        return cql;
    }


    /**
     * 查询节点语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_match_command.html">neo4j -> MATCH命令</a>
     * @param node 节点
     * @return 拼装后的查询语句
     */
    public static String getNodeMatchCql(Node node) {
        StringBuilder cql = new StringBuilder();
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            cql.append(" MATCH (" + node.getName());
            if(node.getLabels() != null && node.getLabels().size() > 0) {
                cql.append(getLabel(node.getLabels()));
            }
            cql.append(")");
        }
        return cql.toString();
    }

    /**
     * 查询关系语句
     * @param relation 关系
     * @return 拼装后的查询语句
     */
    public static String getRelationMatchCql(Relation relation) {
        String cql = "";
        if(relation != null && !StringUtil.isNullOrEmpty(relation.getName())) {
            StringBuilder sb = new StringBuilder();
            sb.append(" MATCH (");
            // 来源节点
            if(relation.getFromNode() != null) {
                Node formNode = relation.getFromNode();
                sb.append(formNode.getName());
                if(formNode.getLabels() != null && formNode.getLabels().size() > 0) {
                    sb.append(getLabel(formNode.getLabels()));
                }
            }
            sb.append(") - [");
            // 关系
            sb.append(relation.getName());
            if(relation.getLabels() != null && relation.getLabels().size() > 0) {
                sb.append(getLabel(relation.getLabels()));
            }
            sb.append("] -> (");
            // 指向节点
            if(relation.getToNode() != null) {
                Node toNode = relation.getToNode();
                sb.append(toNode.getName());
                if(toNode.getLabels() != null && toNode.getLabels().size() > 0) {
                    sb.append(getLabel(toNode.getLabels()));
                }
            }
            sb.append(")");
            cql = sb.toString();
        }
        return cql;
    }

    /**
     * 合并语句，相当于Create + Match。有修改，没有创建
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_merge.html">neo4j -> MERGE命令</a>
     * @param node 节点
     * @return 拼装后的查询语句
     */
    public static String getNodeMergeCql(Node node) {
        StringBuilder cql = new StringBuilder();
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            cql.append(" MERGE (" + node.getName());
            if(node.getLabels() != null && node.getLabels().size() > 0) {
                cql.append(getLabel(node.getLabels()));
            }
            if(node.getProperty() != null && node.getProperty().size() > 0) {
                cql.append(mapToProperty(node.getProperty()));
            }
            cql.append(")");
        }
        return cql.toString();
    }

    /**
     * 合并语句，相当于Create + Match。有修改，没有创建
     * @param relation 关系
     * @return 拼装后的查询语句
     */
    public static String getRelationMergeCql(Relation relation) {
        String cql = "";
        if(relation != null && relation.getFromNode() != null && relation.getToNode() != null) {
            // 来源节点
            Node formNode = relation.getFromNode();
            String formString = "(" + formNode.getName() + getLabel(formNode.getLabels()) + mapToProperty(formNode.getProperty()) + ")";
            // 关系
            String relationString = "[" + relation.getName() + getLabel(relation.getLabels()) + mapToProperty(relation.getProperty()) + "]";
            // 指向节点
            Node toNode = relation.getToNode();
            String toString = "(" + toNode.getName() + getLabel(toNode.getLabels()) + mapToProperty(toNode.getProperty()) + ")";

            cql = "MERGE " + formString + " - " + relationString + " -> "+ toString;
        }
        return cql;
    }

    /**
     * 查询语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_where_clause.html">neo4j -> WHERE子句</a>
     * @param whereCql 查询
     * @return 拼装后的查询语句
     */
    public static String getWhereCql(String whereCql) {
        StringBuilder cql = new StringBuilder();
        if(!StringUtil.isNullOrEmpty(whereCql)) {
            cql.append(" WHERE " + whereCql);
        }
        return cql.toString();
    }

    /**
     * 删除节点属性语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_remove.html">neo4j -> 删除标签/属性</a>
     * @param node 节点
     * @return 拼装后的删除节点属性语句
     */
    public static String getNodeRemovePropertyCql(Node node, List<String> propertys) {
        String cql = "";
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            if(propertys != null && propertys.size() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(" REMOVE ");
                for (String property : propertys) {
                    sb.append(node.getName() + "." + property + ",");
                }
                cql = sb.toString();
                if (cql.endsWith(COMMA)) {
                    cql = cql.substring(0, cql.length() - 1);
                }
            }
        }
        return cql;
    }

    /**
     * 删除节点标签语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_remove.html">neo4j -> 删除标签/属性</a>
     * @param node 节点
     * @return 拼装后的删除节点属性语句
     */
    public static String getNodeRemoveLabelCql(Node node, List<String> propertys) {
        String cql = "";
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            if(propertys != null && propertys.size() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(" REMOVE ");
                for (String property : propertys) {
                    sb.append(node.getName() + ":" + property + ",");
                }
                cql = sb.toString();
                if (cql.endsWith(COMMA)) {
                    cql = cql.substring(0, cql.length() - 1);
                }
            }
        }
        return cql;
    }

    /**
     * 节点设值语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_set.html">neo4j -> 设值子句</a>
     * @param node 节点
     * @return 拼装后的创建语句
     */
    public static String getNodeSetPropertyCql(Node node) {
        String cql = "";
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            String temp = getSet(node.getName(), node.getProperty());
            if(!StringUtil.isNullOrEmpty(temp)) {
                cql = " SET " + temp;
                if (cql.endsWith(COMMA)) {
                    cql = cql.substring(0, cql.length()-1);
                }
            }
        }
        return cql;
    }

    /**
     * 设值标签语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_set.html">neo4j -> 设值子句</a>
     * @param relation 关系
     * @return 拼装后的创建语句
     */
    public static String getRelationSetPropertyCql(Relation relation) {
        String cql = "";
        if(relation != null && !StringUtil.isNullOrEmpty(relation.getName())) {
            StringBuilder sb = new StringBuilder();
            if(relation.getProperty() != null && relation.getProperty().size() > 0) {
                sb.append(getSet(relation.getName(), relation.getProperty()));
                sb.append(",");
            }
            if(relation.getFromNode() != null && relation.getFromNode().getProperty() != null
                && relation.getFromNode().getProperty().size() > 0) {
                sb.append(getSet(relation.getFromNode().getName(), relation.getFromNode().getProperty()));
                sb.append(",");
            }
            if(relation.getToNode() != null && relation.getToNode().getProperty() != null
                    &&relation.getToNode().getProperty().size() > 0) {
                sb.append(getSet(relation.getToNode().getName(), relation.getToNode().getProperty()));
                sb.append(",");
            }
            if(!StringUtil.isNullOrEmpty(sb.toString())) {
                cql = " SET " + sb.toString();
                if (cql.endsWith(COMMA)) {
                    cql = cql.substring(0, cql.length() - 1);
                }
            }
        }
        return cql;
    }

    /**
     * 设值标签语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_set.html">neo4j -> 设值子句</a>
     * @param node 节点
     * @return 拼装后的创建语句
     */
    public static String getNodeSetLabelCql(Node node) {
        String cql = "";
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            if(node.getLabels() != null && node.getLabels().size() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(" SET ");
                for (String label : node.getLabels()) {
                    sb.append(node.getName() + ":" + label);
                    sb.append(",");
                }
                cql = sb.toString();
                if(cql.endsWith(COMMA)) {
                    cql = cql.substring(0, cql.length() - 1);
                }
            }
        }
        return cql;
    }

    /**
     * 返回语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_return_clause.html">neo4j -> return子句</a>
     * @param node 节点
     * @param returnMap 需要返回的属性名 key：属性名 value:别名
     * @return 拼装后的查询语句
     */
    public static String getNodeReturnCql(Node node, Map<String, String> returnMap) {
        String cql = "";
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            cql += " RETURN " + getReturn(node.getName(), returnMap);
            if (cql.endsWith(COMMA)) {
                cql = cql.substring(0, cql.length() - 1);
            }
        }
        return cql;
    }

    /**
     * 返回关系语句
     * @param relation 节点
     * @param fromNodeMap 需要返回的来源节点属性名 key：属性名 value:别名
     * @param toNodeMap 需要返回的指向节点属性名 key：属性名 value:别名
     * @return 拼装后的查询语句
     */
    public static String getRelationReturnCql(Relation relation, Map<String, String> relationMap, Map<String, String> fromNodeMap, Map<String, String> toNodeMap) {
        String cql = "";
        if(relation != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(" RETURN ");
            // 来源节点
            if(relation.getFromNode() != null && !StringUtil.isNullOrEmpty(relation.getFromNode().getName())) {
                sb.append(getReturn(relation.getFromNode().getName(), fromNodeMap));
                sb.append(",");
            }
            // 指向节点
            if(relation.getToNode() != null && !StringUtil.isNullOrEmpty(relation.getToNode().getName())) {
                sb.append(getReturn(relation.getToNode().getName(), toNodeMap));
                sb.append(",");
            }
            // 关系
            if(!StringUtil.isNullOrEmpty(relation.getName())) {
                sb.append(getReturn(relation.getName(), relationMap));
                sb.append(",");
            }
            cql = sb.toString();
            if (cql.endsWith(COMMA)) {
                cql = cql.substring(0, cql.length() - 1);
            }
        }
        return cql;
    }

    /**
     * 删除关系语句
     * @see <a href="https://www.w3cschool.cn/neo4j/delete.html">neo4j -> 删除子句</a>
     * @param node 节点
     * @return 拼装后的删除节点语句
     */
    public static String getNodeDeleteCql(Node node) {
        StringBuilder cql = new StringBuilder();
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            cql.append(" DELETE " + node.getName());
        }
        return cql.toString();
    }

    /**
     * 删除关系语句
     * @see <a href="https://www.w3cschool.cn/neo4j/delete.html">neo4j -> 删除子句</a>
     * @param relation 关系
     * @return 拼装后的删除节点语句
     */
    public static String getRelationDeleteCql(Relation relation) {
        StringBuilder cql = new StringBuilder();
        if(relation != null && !StringUtil.isNullOrEmpty(relation.getName())) {
            cql.append(" DELETE " + relation.getName());
        }
        return cql.toString();
    }

    /**
     * 排序语句
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_sorting.html">neo4j -> 排序</a>
     * @param node 节点
     * @param sortCql key:参与排序的字段 value:大于等于0正序，否则倒序
     * @return 拼装后的排序语句
     */
    public static String getNodeSortCql(Node node, Map<String, Integer> sortCql) {
        String cql = "";
        if(node != null && !StringUtil.isNullOrEmpty(node.getName())) {
            if(sortCql != null && sortCql.size() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(" ORDER BY ");
                for (Map.Entry<String, Integer> value : sortCql.entrySet()) {
                    sb.append(node.getName() + "." + value.getKey());
                    // 倒序
                    if(value.getValue() < 0) {
                        sb.append(" DESC");
                    }
                    sb.append(",");
                }
                cql = sb.toString();
                if(cql.endsWith(COMMA)) {
                    cql = cql.substring(0, cql.length() - 1);
                }
            }
        }
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

    /**
     * 将标签集合改为符合cql的集合格式
     * @param labelList 标签集合
     * @return 符合cql的标签格式
     */
    public static String getLabel(List<String> labelList) {
        String labelCql = "";
        if(labelList != null && labelList.size() > 0) {
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
     * 将属性集合改为符合cql的集合格式
     * @param propertyMap 属性集合
     * @return 符合cql的属性格式
     */
    public static String mapToProperty(Map<String, Object> propertyMap) {
        String cql = "";
        if(propertyMap != null && propertyMap.size() > 0) {
            cql += "{";
            StringBuilder property = new StringBuilder();
            for (Map.Entry<String, Object> value : propertyMap.entrySet()) {
                property.append(value.getKey() + ":");
                property.append(getProperty(value.getValue()) + ",");
            }
            String result = property.toString();
            if(result.endsWith(COMMA)) {
                result = result.substring(0, result.length()-1);
            }
            cql += result + "}";
        }
        return cql;
    }

    /**
     * 将属性转换为字符形式输出
     * @param value 属性值
     * @return 属性字符值
     */
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
     * 组装返回语句
     * @param name 名称
     * @param returnMap 需要返回的属性名 key：属性名 value:别名
     * @return 返回语句
     */
    public static String getReturn(String name, Map<String, String> returnMap) {
        String cql = "";
        StringBuilder sb = new StringBuilder();
        if(returnMap != null && returnMap.size() > 0) {
            for (Map.Entry<String, String> value : returnMap.entrySet()) {
                sb.append(name + "." + value.getKey());
                if(value.getValue() != null && !"".equals(value.getValue())) {
                    sb.append(" as " + value.getValue());
                }
                sb.append(",");
            }
            if (cql.endsWith(COMMA)) {
                cql = cql.substring(0, cql.length() - 1);
            }
        } else {
            sb.append(name);
        }
        cql = sb.toString();
        return cql;
    }

    /**
     * 组装设置语句
     * @param name 名称
     * @param propertyMap 属性 key：属性名 value:别名
     * @return 返回语句
     */
    public static String getSet(String name, Map<String, Object> propertyMap) {
        String cql = "";
        if(propertyMap != null && propertyMap.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> value : propertyMap.entrySet()) {
                sb.append(name + "." + value.getKey() + "=");
                sb.append(getProperty(value.getValue()));
                sb.append(",");
            }
            cql = sb.toString();
            if (cql.endsWith(COMMA)) {
                cql = cql.substring(0, cql.length() - 1);
            }
        }
        return cql;
    }
}
