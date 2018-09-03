package com.heqing.nosql.neo4j;

import java.util.List;

/**
 * 查询条件
 * @author heqing
 * @date 2018/9/3 14:15
 */
public class QueryUtil {

    /**
     * 等于
     * @param nodeName 节点名
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String eq(String nodeName, String field, Object value) {
        return nodeName + "." + field + "=" + Neo4jUtil.getProperty(value);
    }

    /**
     * 不等于
     * @param nodeName 节点名
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String ne(String nodeName, String field, Object value) {
        return nodeName + "." + field + "<>" + Neo4jUtil.getProperty(value);
    }

    /**
     * 小于
     * @param nodeName 节点名
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String lt(String nodeName, String field, Double value) {
        return nodeName + "." + field + "<" + value;
    }

    /**
     * 小于或等于
     * @param nodeName 节点名
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String lte(String nodeName, String field, Double value) {
        return nodeName + "." + field + "<=" + value;
    }

    /**
     * 大于
     * @param nodeName 节点名
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String gt(String nodeName, String field, Double value) {
        return nodeName + "." + field + ">" + value;
    }

    /**
     * 大于或等于
     * @param nodeName 节点名
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String gte(String nodeName, String field, Double value) {
        return nodeName + "." + field + ">=" + Neo4jUtil.getProperty(value);
    }

    /**
     * 等于null
     * @param nodeName 节点名
     * @param field 字段名
     * @return 查询条件
     */
    public static String isNull(String nodeName, String field) {
        return nodeName + "." + field + " IS NULL ";
    }

    /**
     * 不等于null
     * @param nodeName 节点名
     * @param field 字段名
     * @return 查询条件
     */
    public static String isNotNull(String nodeName, String field) {
        return nodeName + "." + field + " IS NOT NULL ";
    }

    /**
     * 属于
     * @param nodeName 节点名
     * @param field 字段名
     * @param values 值
     * @return 查询条件
     */
    public static String in(String nodeName, String field, List<Object> values) {
        return nodeName + "." + field + " IN " + values.toString();
    }

    /**
     * 和
     * @param fields 条件
     * @return 多个查询条件
     */
    public static String and(String... fields) {
        String cql = "";
        if(fields.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String f : fields) {
                sb.append("(" + f + ")");
                sb.append(" AND");
            }
            cql = sb.toString();
            if(cql.endsWith(" AND")) {
                cql = cql.substring(0, cql.length()-3);
            }
        }
        return cql;
    }

    /**
     * 或
     * @param fields 条件
     * @return 多个查询条件
     */
    public static String or(String... fields) {
        String cql = "";
        if(fields.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String f : fields) {
                sb.append("(" + f + ")");
                sb.append(" OR");
            }
            cql = sb.toString();
            if(cql.endsWith(" OR")) {
                cql = cql.substring(0, cql.length()-2);
            }
        }
        return cql;
    }

    /**
     * 非
     * @param fields 条件
     * @return 多个查询条件
     */
    public static String not(String... fields) {
        String cql = "";
        if(fields.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String f : fields) {
                sb.append("(" + f + ")");
                sb.append(" NOT");
            }
            cql = sb.toString();
            if(cql.endsWith(" NOT")) {
                cql = cql.substring(0, cql.length()-3);
            }
        }
        return cql;
    }

    /**
     * 异或
     * @param fields 条件
     * @return 多个查询条件
     */
    public static String xor(String... fields) {
        String cql = "";
        if(fields.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String f : fields) {
                sb.append("(" + f + ")");
                sb.append(" XOR");
            }
            cql = sb.toString();
            if(cql.endsWith(" XOR")) {
                cql = cql.substring(0, cql.length()-3);
            }
        }
        return cql;
    }

    /**
     * 它将两组结果中的公共行组合并返回到一组结果中。 它不从两个节点返回重复的行。
     * @param cqls 多个查询语句
     * @return 组合条件
     */
    public static String union(String... cqls) {
        String cql = "";
        if(cqls.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String c : cqls) {
                sb.append("(" + c + ")");
                sb.append(" UNION");
            }
            cql = sb.toString();
            if(cql.endsWith(" UNION")) {
                cql = cql.substring(0, cql.length()-5);
            }
        }
        return cql;
    }

    /**
     * 它结合并返回两个结果集的所有行成一个单一的结果集。它还返回由两个节点重复行。
     * @param cqls 多个查询语句
     * @return 组合条件
     */
    public static String unionAll(String... cqls) {
        String cql = "";
        if(cqls.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String c : cqls) {
                sb.append("(" + c + ")");
                sb.append(" UNION ALL");
            }
            cql = sb.toString();
            if(cql.endsWith(" UNION ALL")) {
                cql = cql.substring(0, cql.length()-9);
            }
        }
        return cql;
    }
}
