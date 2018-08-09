package com.heqing.nosql.mongodb;

import com.mongodb.client.MongoDatabase;
import org.junit.Test;

import java.util.List;

/**
 * @author heqing
 * @date 2018/8/9 16:03
 */
public class TestDataBase {

    @Test
    public void testGetDBAllName() {
        List<String> dbNameList = MongodbUtil.getDataBase().getDBAllName();
        System.out.println("--->"+dbNameList);
    }

    @Test
    public void testMongoByName() {
        MongoDatabase db = MongodbUtil.getDataBase().getMongoByName("hq_test");
        System.out.println("--->"+(db != null));
    }

    @Test
    public void testDeleteMongoByName() {
        MongodbUtil.getDataBase().deleteMongoByName("hq_test");
    }
}
