package com.heqing.nosql.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Test;

import java.util.List;

/**
 * @author heqing
 * @date 2018/8/9 16:47
 */
public class TestCollection {

    @Test
    public void testCreateCollection() {
        MongodbUtil.getCollection().createCollection("hq_test", "test1");
        MongodbUtil.getCollection().createCollection("hq_test", "test2");
        boolean result = MongodbUtil.getCollection().createCollection("hq_test", "test2");
        System.out.println("result = "+result);
    }

    @Test
    public void testDeleteCollection() {
        boolean result = MongodbUtil.getCollection().deleteCollection("hq_test", "test2");
        System.out.println("result = "+result);

        result = MongodbUtil.getCollection().deleteCollection("hq_test", "test2");
        System.out.println("result = "+result);
    }

    @Test
    public void testGetCollectionByName() {
        MongoCollection<Document> coll = MongodbUtil.getCollection().getCollectionByName("hq_test", "test1");
        System.out.println("库名--->"+coll.getNamespace().getDatabaseName());
        System.out.println("集合名--->"+coll.getNamespace().getCollectionName());
        System.out.println("全名--->"+coll.getNamespace().getFullName());
        System.out.println("文档数--->"+coll.countDocuments());
    }

    @Test
    public void testGetAllCollections() {
        List<String> collList = MongodbUtil.getCollection().getAllCollections("hq_test");
        System.out.println("--->"+collList);
    }
}
