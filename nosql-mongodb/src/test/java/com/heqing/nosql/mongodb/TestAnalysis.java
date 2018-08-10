package com.heqing.nosql.mongodb;

import org.junit.Test;

/**
 * @author heqing
 * @date 2018/8/10 17:11
 */
public class TestAnalysis {

    private final static String WHERE = "WHERE";
    private final static String ORDER_BY = "ORDER BY";
    private final static String LIMIT = "LIMIT";
    private final static String SPRINT = ",";

    @Test
    public void analysisSQL() {
        String sql = "WHERE name='heqing' OR (age>=20 AND age<=80) ORDER BY id ASC, age DESC LIMIT 1,10";
        int whereIndex = -1;
        int orderIndex = -1;
        int limitIndex = -1;
        if(sql.contains(WHERE)) {
            whereIndex = 0;
        }
        if(sql.contains(ORDER_BY)) {
            orderIndex = sql.indexOf("ORDER BY");
        }
        if(sql.contains(LIMIT)) {
            limitIndex = sql.indexOf("LIMIT");
        }
        if(whereIndex > -1) {
            System.out.println("--------where---------");
            int start = whereIndex+6;
            int end = sql.length();
            if(orderIndex > -1) {
                end = orderIndex;
            }
            if(orderIndex < 0 && limitIndex > -1) {
                end = limitIndex;
            }
            String a = sql.substring(start, end);
            System.out.println(a);
        }
        if(orderIndex > -1) {
            System.out.println("--------orderBy---------");
            int start = orderIndex+8;
            int end = sql.length();
            if(limitIndex > -1) {
                end = limitIndex;
            }
            String orderSql = sql.substring(start, end);
            if(orderSql.contains(SPRINT)) {
                String[] orderList = orderSql.split(",");
                for(String order : orderList) {
                    String[] tempOrderList = order.split(" ");
                    String field = tempOrderList[1];
                    String by = tempOrderList[2];
                    System.out.println(field+":"+by);
                }
            }
        }
        if(limitIndex > -1) {
            System.out.println("--------limit---------");
            int start = limitIndex+6;
            int end = sql.length();
            String limitSql = sql.substring(start, end);
            String[] limitList = limitSql.split(",");
            System.out.println(limitList[0]+":"+limitList[1]);
        }

    }
}
