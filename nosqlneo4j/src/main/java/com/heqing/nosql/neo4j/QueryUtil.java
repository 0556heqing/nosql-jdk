package com.heqing.nosql.neo4j;

import java.util.List;

/**
 * 查询条件
 * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_where_clause.html">neo4j -> where子句</a>
 * @author heqing
 * @date 2018/9/3 14:15
 */
public class QueryUtil {

    private static final String AND = " AND ";
    private static final String OR = " OR ";
    private static final String NOT = " NOT ";
    private static final String XOR = " XOR ";
    private static final String UNION = " UNION ";
    private static final String UNION_ALL = " UNION ALL ";

    /**
     * 主键id等于
     * @param name 名称
     * @param value 值
     * @return 查询条件
     */
    public static String eq(String name, Long value) {
        return "id(" + name + ") = " + value;
    }

    /**
     * 等于
     * @param name 名称
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String eq(String name, String field, Object value) {
        return name + "." + field + "=" + LanguageUtil.getProperty(value);
    }

    /**
     * 主键id不等于
     * @param name 名称
     * @param value 值
     * @return 查询条件
     */
    public static String ne(String name, Long value) {
        return "id(" + name + ") <> " + value;
    }

    /**
     * 不等于
     * @param name 名称
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String ne(String name, String field, Object value) {
        return name + "." + field + "<>" + LanguageUtil.getProperty(value);
    }

    /**
     * 主键id小于
     * @param name 名称
     * @param value 值
     * @return 查询条件
     */
    public static String lt(String name, Long value) {
        return "id(" + name + ") < " + value;
    }

    /**
     * 小于
     * @param name 名称
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String lt(String name, String field, Double value) {
        return name + "." + field + "<" + value;
    }

    /**
     * 主键id小于或等于
     * @param name 名称
     * @param value 值
     * @return 查询条件
     */
    public static String lte(String name, Long value) {
        return "id(" + name + ") <= " + value;
    }

    /**
     * 小于或等于
     * @param name 名称
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String lte(String name, String field, Double value) {
        return name + "." + field + "<=" + value;
    }

    /**
     * 主键id大于
     * @param name 名称
     * @param value 值
     * @return 查询条件
     */
    public static String gt(String name, Long value) {
        return "id(" + name + ") > " + value;
    }

    /**
     * 大于
     * @param name 名称
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String gt(String name, String field, Double value) {
        return name + "." + field + ">" + value;
    }

    /**
     * 主键id大于或等于
     * @param name 名称
     * @param value 值
     * @return 查询条件
     */
    public static String gte(String name, Long value) {
        return "id(" + name + ") >= " + value;
    }

    /**
     * 大于或等于
     * @param name 名称
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static String gte(String name, String field, Double value) {
        return name + "." + field + ">=" + LanguageUtil.getProperty(value);
    }

    /**
     * 等于null
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_null.html">neo4j -> null</a>
     * @param name 名称
     * @param field 字段名
     * @return 查询条件
     */
    public static String isNull(String name, String field) {
        return name + "." + field + " IS NULL";
    }

    /**
     * 不等于null
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_null.html">neo4j -> not null</a>
     * @param nodeName 节点名
     * @param field 字段名
     * @return 查询条件
     */
    public static String isNotNull(String nodeName, String field) {
        return nodeName + "." + field + " IS NOT NULL";
    }

    /**
     * 主键id属于
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_in_operator.html">neo4j -> in</a>
     * @param name 名称
     * @param values 值
     * @return 查询条件
     */
    public static String in(String name, List<Long> values) {
        String cql = "";
        if(values != null && values.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(Object value : values) {
                sb.append(" " + LanguageUtil.getProperty(value) +",");
            }
            cql = sb.toString();
            if(cql.endsWith(LanguageUtil.COMMA)) {
                cql = cql.substring(0, cql.length()-1);
            }
            cql = "id(" + name + ") IN " + cql + "]";
        }
        return cql;
    }

    /**
     * 属于
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_in_operator.html">neo4j -> in</a>
     * @param name 名称
     * @param field 字段名
     * @param values 值
     * @return 查询条件
     */
    public static String in(String name, String field, List<Object> values) {
        String cql = "";
        if(values != null && values.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(Object value : values) {
                sb.append(" " + LanguageUtil.getProperty(value) +",");
            }
            cql = sb.toString();
            if(cql.endsWith(LanguageUtil.COMMA)) {
                cql = cql.substring(0, cql.length()-1);
            }
            cql = name + "." + field + " IN " + cql + "]";
        }
        return cql;
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
                sb.append(" AND ");
            }
            cql = sb.toString();
            if(cql.endsWith(AND)) {
                cql = cql.substring(0, cql.length()-4);
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
                sb.append(" OR ");
            }
            cql = sb.toString();
            if(cql.endsWith(OR)) {
                cql = cql.substring(0, cql.length()-3);
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
                sb.append(" NOT ");
            }
            cql = sb.toString();
            if(cql.endsWith(NOT)) {
                cql = cql.substring(0, cql.length()-4);
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
                sb.append(" XOR ");
            }
            cql = sb.toString();
            if(cql.endsWith(XOR)) {
                cql = cql.substring(0, cql.length()-4);
            }
        }
        return cql;
    }

    /**
     * 它将两组结果中的公共行组合并返回到一组结果中。 它不从两个节点返回重复的行。
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_union.html">neo4j -> 合并</a>
     * @param cqls 多个查询语句
     * @return 组合条件
     */
    public static String union(String... cqls) {
        String cql = "";
        if(cqls.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String c : cqls) {
                sb.append("(" + c + ")");
                sb.append(" UNION ");
            }
            cql = sb.toString();
            if(cql.endsWith(UNION)) {
                cql = cql.substring(0, cql.length()-6);
            }
        }
        return cql;
    }

    /**
     * 它结合并返回两个结果集的所有行成一个单一的结果集。它还返回由两个节点重复行。
     * @see <a href="https://www.w3cschool.cn/neo4j/neo4j_cql_union.html">neo4j -> 合并</a>
     * @param cqls 多个查询语句
     * @return 组合条件
     */
    public static String unionAll(String... cqls) {
        String cql = "";
        if(cqls.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String c : cqls) {
                sb.append("(" + c + ")");
                sb.append(" UNION ALL ");
            }
            cql = sb.toString();
            if(cql.endsWith(UNION_ALL)) {
                cql = cql.substring(0, cql.length()-10);
            }
        }
        return cql;
    }
}
