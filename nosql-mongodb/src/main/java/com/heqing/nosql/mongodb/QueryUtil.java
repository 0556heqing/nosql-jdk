package com.heqing.nosql.mongodb;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * @author heqing
 * @date 2018/8/10 14:19
 */
public class QueryUtil {

    /**
     * 等于
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson eq(String field, Object value) {
        return Filters.eq(field, value);
    }

    /**
     * 不等于
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson ne(String field, Object value) {
        return Filters.ne(field, value);
    }

    /**
     * 小于
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson lt(String field, Double value) {
        return Filters.lt(field, value);
    }

    /**
     * 小于或等于
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson lte(String field, Double value) {
        return Filters.lte(field, value);
    }

    /**
     * 大于
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson gt(String field, Double value) {
        return Filters.gt(field, value);
    }

    /**
     * 大于或等于
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson gte(String field, Double value) {
        return Filters.gte(field, value);
    }

    /**
     * 包含
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson like(String field, String value) {
        return Filters.regex(field, "^.*"+value+".*$");
    }

    /**
     * 以某某开头
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson startsWith(String field, String value) {
        return Filters.regex(field, "^"+value+".*$");
    }

    /**
     * 以某某结尾
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson endsWith(String field, String value) {
        return Filters.regex(field, "^.*"+value+"$");
    }

    /**
     * 以字段名正序排列
     * @param field 字段名
     * @return 查询条件
     */
    public static Bson asc(String field) {
        return Filters.eq(field, 1);
    }

    /**
     * 以字段名倒序排列
     * @param field 字段名
     * @return 查询条件
     */
    public static Bson desc(String field) {
        return Filters.eq(field, -1);
    }

    /**
     * 在 startValue 和 endValue 之内的数，包括startValue/endValue
     * @param field 字段名
     * @param startValue 开始值
     * @param endValue 结束值
     * @return 查询条件
     */
    public static Bson between(String field, Double startValue, Double endValue) {
        Bson start = Filters.lte(field, startValue);
        Bson end = Filters.gte(field, endValue);
        return Filters.and(start, end);
    }

    /**
     * field 中存在 value 中的值
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson in(String field, Object value) {
        return Filters.in(field, value);
    }

    /**
     * field 中存在 valueList 中的值
     * @param field 字段名
     * @param valueList 值列表
     * @return 查询条件
     */
    public static Bson in(String field, List<Object> valueList) {
        return Filters.in(field, valueList);
    }

    /**
     * field 中不存在 valueList 中的值
     * @param field 字段名
     * @param value 值
     * @return 查询条件
     */
    public static Bson nin(String field, Object value) {
        return Filters.nin(field, value);
    }

    /**
     * field 中不存在 valueList 中的值
     * @param field 字段名
     * @param valueList 值列表
     * @return 查询条件
     */
    public static Bson nin(String field, List<Object> valueList) {
        return Filters.in(field, valueList);
    }
}
