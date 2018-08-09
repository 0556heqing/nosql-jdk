package com.heqing.nosql.mongodb.service.impl;

import com.heqing.nosql.mongodb.service.CollectionService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heqing
 * @date 2018/8/9 15:44
 */
public class CollectionServiceImpl extends DataBaseServiceImpl implements CollectionService {

    private Logger logger = LoggerFactory.getLogger(CollectionServiceImpl.class);

    @Override
    public boolean createCollection(String dbName, String collectionName) {
        boolean result = true;
        try {
            getMongoByName(dbName).createCollection(collectionName);
        } catch (Exception e) {
            result = false;
            logger.error("创建集合失败 ---> " + e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean deleteCollection(String dbName, String collectionName) {
        boolean result = true;
        try {
        MongoCollection<Document> collection = getMongoByName(dbName).getCollection(collectionName);
        if(collection != null) {
            collection.drop();
        }
        } catch (Exception e) {
            result = false;
            logger.error("删除集合失败 ---> " + e.getMessage(), e);
        }
        return result;
    }

    @Override
    public MongoCollection<Document> getCollectionByName(String dbName, String collName) {
        if (null == dbName || "".equals(dbName)) {
            return null;
        }
        if (null == collName || "".equals(collName)) {
            return null;
        }
        MongoCollection<Document> collection = getMongoByName(dbName).getCollection(collName);
        return collection;
    }

    @Override
    public List<String> getAllCollections(String dbName) {
        MongoIterable<String> colls = getMongoByName(dbName).listCollectionNames();
        List<String> collsList = new ArrayList<>();
        for (String s : colls) {
            collsList.add(s);
        }
        return collsList;
    }

}
