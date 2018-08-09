package com.heqing.nosql.mongodb.service.impl;

import com.heqing.nosql.mongodb.MongodbUtil;
import com.heqing.nosql.mongodb.service.DataBaseService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heqing
 * @date 2018/8/9 15:44
 */
public class DataBaseServiceImpl implements DataBaseService {

    protected MongoClient mongoClient = null;

    protected MongoClient getMongoClient() {
        if(mongoClient == null) {
            mongoClient = MongodbUtil.getMongoClient();
        }
        return mongoClient;
    }

    @Override
    public void deleteMongoByName(String dbName) {
        getMongoClient().dropDatabase(dbName);
    }

    @Override
    public List<String> getDBAllName() {
        MongoIterable<String> dbList = getMongoClient().listDatabaseNames();
        List<String> dbNameList = new ArrayList<>();
        for (String db : dbList) {
            dbNameList.add(db);
        }
        return dbNameList;
    }

    @Override
    public MongoDatabase getMongoByName(String dbName) {
        if (dbName != null && !"".equals(dbName)) {
            MongoDatabase database = getMongoClient().getDatabase(dbName);
            return database;
        }
        return null;
    }

}
