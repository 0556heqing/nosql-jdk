package com.heqing.nosql.mongodb;

import com.heqing.nosql.mongodb.constant.Comparison;
import com.heqing.nosql.mongodb.constant.ComparisonDigital;
import com.heqing.nosql.mongodb.constant.Order;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.regex.Pattern;

/**
 * @author heqing
 * @date 2018/8/10 14:19
 */
public class QueryUtil {

    /**
     * 该方法用于生成查询条件
     * @param field 字段名
     * @param order 正序/倒序
     * @return 查询条件
     */
    public static Bson order(String field, Order order) {
        Bson bson = null;
        switch(order) {
            case asc : bson = Filters.eq(field, 1); break;
            case desc : bson = Filters.eq(field, -1); break;
            default: break;
        }
        return bson;
    }

    /**
     * 该方法用于生成比较数组条件
     * @param field 字段名
     * @param value 比较值
     * @param condition 比较条件
     * @return 查询条件
     */
    public static Bson comparisonDigital(String field, Double value, ComparisonDigital condition) {
        Bson bson = null;
        switch(condition) {
            case lt : bson = Filters.lt(field, value); break;
            case lte : bson = Filters.lte(field, value); break;
            case gt : bson = Filters.gt(field, value); break;
            case gte : bson = Filters.gte(field, value); break;
            case eq : bson = Filters.eq(field, value); break;
            case ne : bson = Filters.ne(field, value); break;
            default: break;
        }
        return bson;
    }

    /**
     * 该方法用于生成比较字符条件
     * @param field 字段名
     * @param value 比较值
     * @param condition 比较条件
     * @return 查询条件
     */
    public static Bson comparison(String field, String value, Comparison condition) {
        Bson bson = null;
        Pattern pattern = null;
        BasicDBObject query = new BasicDBObject();
        switch(condition) {
            case eq : bson = Filters.eq(field, value); break;
            case ne : bson = Filters.ne(field, value); break;
            case start :
                pattern = Pattern.compile("^"+value+".*$", Pattern.CASE_INSENSITIVE);
                query.put(field, pattern);
                bson = query;
                break;
            case like :
                pattern = Pattern.compile("^.*"+value+".*$", Pattern.CASE_INSENSITIVE);
                query.put(field, pattern);
                bson = query;
                break;
            case end :
                pattern = Pattern.compile("^.*"+value+"$", Pattern.CASE_INSENSITIVE);
                query.put(field, pattern);
                bson = query;
                break;
            default: break;
        }
        return bson;
    }
}
